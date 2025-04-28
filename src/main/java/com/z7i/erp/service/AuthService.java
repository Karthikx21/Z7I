package com.z7i.erp.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.z7i.erp.model.Users;
import com.z7i.erp.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, OtpService otpService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
    }

    public Optional<Users> authenticate(String username, String password) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public boolean validateTotp(String username, String otp) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            String secret = user.getTotpSecret();
            if (secret == null || secret.isEmpty()) {
                return false;
            }
            return otpService.validateOtp(secret, otp);
        }
        return false;
    }

    public Optional<Users> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String generateAndSaveSecretForUser(String username) {
        Optional<Users> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            String secret = user.getTotpSecret();
            if (secret == null || secret.isEmpty()) {
                secret = otpService.generateSecretForUser(username);
                user.setTotpSecret(secret);
                userRepository.save(user);
            }
            return secret;
        }
        return null;
    }

    public boolean resetPassword(String username, String email, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return false;
        }
        Optional<Users> userOpt = userRepository.findByUsernameAndEmail(username, email);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
