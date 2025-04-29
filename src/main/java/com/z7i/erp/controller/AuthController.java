package com.z7i.erp.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.z7i.erp.dto.ForgotPasswordRequest;
import com.z7i.erp.dto.LoginRequest;
import com.z7i.erp.dto.LoginSuccessResponse;
import com.z7i.erp.dto.OtpRequest;
import com.z7i.erp.dto.UserDto;
import com.z7i.erp.model.Users;
import com.z7i.erp.service.AuthService;
import com.z7i.erp.service.JwtService;
import com.z7i.erp.service.OtpQrCodeService;
import com.z7i.erp.service.OtpService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final OtpService otpService;
    private final OtpQrCodeService otpQrCodeService;
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(OtpService otpService, OtpQrCodeService otpQrCodeService, JwtService jwtService, AuthService authService) {
        this.otpService = otpService;
        this.otpQrCodeService = otpQrCodeService;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        logger.info("Forgot password request received for user: {}", request.getUsername());
        boolean success = authService.resetPassword(request.getUsername(), request.getNewPassword(), request.getConfirmPassword());
        if (success) {
            logger.info("Password changed successfully for user: {}", request.getUsername());
            return ResponseEntity.ok("Password changed successfully");
        } else {
            logger.warn("Failed password change attempt for user: {}", request.getUsername());
            return ResponseEntity.badRequest().body("Invalid username or passwords do not match");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        logger.info("Login attempt for user: {}", request.getUsername());
        Optional<Users> userOpt = authService.authenticate(request.getUsername(), request.getPassword());
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            logger.info("User {} authenticated successfully", request.getUsername());
            String token = jwtService.generateToken(user);
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
            );
            LoginSuccessResponse response = new LoginSuccessResponse("success", token, userDto);
            return ResponseEntity.ok(response);
        } else {
            logger.warn("Invalid login attempt for user: {}", request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/verify-totp")
    public ResponseEntity<String> verifyTotp(@Valid @RequestBody OtpRequest request) {
        logger.info("Verifying TOTP for user: {}", request.getUsername());
        boolean valid = authService.validateTotp(request.getUsername(), request.getOtp());
        if (valid) {
            Optional<Users> userOpt = authService.getUserByUsername(request.getUsername());
            if (userOpt.isPresent()) {
                Users user = userOpt.get();
                String token = jwtService.generateToken(user);
                logger.info("TOTP verified successfully for user: {}", request.getUsername());
                return ResponseEntity.ok(token);
            } else {
                logger.error("User not found after OTP verification: {}", request.getUsername());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found");
            }
        } else {
            logger.warn("Invalid TOTP for user: {}", request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }
    }

    @GetMapping("/generate-qr-code")
    public ResponseEntity<byte[]> generateQRCode(@RequestParam String usernameOrEmail,
                                                 @RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized".getBytes());
        }
        String secretKey = authService.generateAndSaveSecretForUser(usernameOrEmail);
        if (secretKey == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found".getBytes());
        }
        return otpQrCodeService.generateQRCode(usernameOrEmail, secretKey);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("Exception occurred: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}
