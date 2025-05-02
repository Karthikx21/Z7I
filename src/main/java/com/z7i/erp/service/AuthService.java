package com.z7i.erp.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.z7i.erp.dto.ForgotPasswordRequest;
import com.z7i.erp.dto.LoginRequest;
import com.z7i.erp.dto.LoginSuccessResponse;
import com.z7i.erp.dto.OtpRequest;
import com.z7i.erp.dto.UserDto;
import com.z7i.erp.model.Users;

import com.z7i.erp.repository.UserRepository;

@Service
public class AuthService implements AuthServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final JwtService jwtService;

    // In-memory cache for temporary TOTP secrets keyed by username
    private final Map<String, String> tempSecrets = new ConcurrentHashMap<>();

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, OtpService otpService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.jwtService = jwtService;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'EMPLOYEE', 'STUDENT')")
    public Optional<Users> authenticate(String usernameOrEmail, String password) {
        Optional<Users> userOpt = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public boolean assignRoleToUser(String username, String roleName) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setRole(roleName);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public boolean removeRoleFromUser(String username, String roleName) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            if (user.getRole().equals(roleName)) {
                user.setRole(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'EMPLOYEE', 'STUDENT')")
    public LoginSuccessResponse login(LoginRequest request) {
        Optional<Users> userOpt = authenticate(request.getUsername(), request.getPassword());
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            String token = jwtService.generateToken(user);
            boolean needsQrSetup = user.getTotpSecret() == null || user.getTotpSecret().isEmpty();
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole(),
                    needsQrSetup
            );
            return new LoginSuccessResponse("success", token, userDto);
        }
        return null;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'EMPLOYEE', 'STUDENT')")
    public boolean validateTotp(String username, String otp) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            String secret = tempSecrets.get(username);
            if (secret == null || secret.isEmpty()) {
                secret = user.getTotpSecret();
            }
            if (secret == null || secret.isEmpty()) {
                return false;
            }
            logger.debug("Validating OTP: {} against secret: {}", otp, secret);
            boolean result = otpService.validateOtp(secret, otp);
            logger.debug("OTP validation result: {}", result);
            return result;
        }
        return false;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'EMPLOYEE', 'STUDENT')")
    public String verifyTotp(OtpRequest request) {
        boolean valid = validateTotp(request.getUsername(), request.getOtp());
        if (valid) {
            boolean saved = saveTotpSecretForUser(request.getUsername());
            if (saved) {
                logger.info("Temporary TOTP secret saved permanently for user: {}", request.getUsername());
            } else {
                logger.warn("No temporary TOTP secret to save for user: {}", request.getUsername());
            }
            Optional<Users> userOpt = getUserByUsername(request.getUsername());
            if (userOpt.isPresent()) {
                Users user = userOpt.get();
                return jwtService.generateToken(user);
            } else {
                logger.error("User not found after OTP verification: {}", request.getUsername());
            }
        } else {
            logger.warn("Invalid TOTP for user: {}", request.getUsername());
        }
        return null;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'EMPLOYEE', 'STUDENT')")
    public Optional<Users> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'EMPLOYEE', 'STUDENT')")
    public String generateSecretForUser(String usernameOrEmail) {
        Optional<Users> userOpt = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            String secret = tempSecrets.get(user.getUsername());
            if (secret == null || secret.isEmpty()) {
                secret = otpService.generateSecretForUser(user.getUsername());
                tempSecrets.put(user.getUsername(), secret);
            }
            return secret;
        }
        return null;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'EMPLOYEE', 'STUDENT')")
    public boolean saveTotpSecretForUser(String username) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            String tempSecret = tempSecrets.get(username);
            if (tempSecret != null && !tempSecret.isEmpty()) {
                user.setTotpSecret(tempSecret);
                userRepository.save(user);
                tempSecrets.remove(username);
                return true;
            }
        }
        return false;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'EMPLOYEE', 'STUDENT')")
    public boolean resetPassword(ForgotPasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return false;
        }
        Optional<Users> userOpt = userRepository.findByUsername(request.getUsername());
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
