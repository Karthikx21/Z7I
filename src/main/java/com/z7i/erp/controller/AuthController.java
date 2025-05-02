package com.z7i.erp.controller;

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
import com.z7i.erp.service.AuthServiceInterface;
import com.z7i.erp.service.OtpQrCodeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    private final OtpQrCodeService otpQrCodeService;
    private final AuthServiceInterface authService;

    public AuthController(OtpQrCodeService otpQrCodeService, AuthServiceInterface authService) {
        this.otpQrCodeService = otpQrCodeService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        logger.info("Login attempt for user: {}", request.getUsername());
        LoginSuccessResponse response = authService.login(request);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            logger.warn("Invalid login attempt for user: {}", request.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        logger.info("Forgot password request received for user: {}", request.getUsername());
        boolean success = authService.resetPassword(request);
        if (success) {
            logger.info("Password changed successfully for user: {}", request.getUsername());
            return ResponseEntity.ok("Password changed successfully");
        } else {
            logger.warn("Failed password change attempt for user: {}", request.getUsername());
            return ResponseEntity.badRequest().body("Invalid username or passwords do not match");
        }
    }


    @PostMapping("/verify-totp")
    public ResponseEntity<String> verifyTotp(@Valid @RequestBody OtpRequest request) {
        logger.info("Verifying TOTP for user: {}", request.getUsername());
        String token = authService.verifyTotp(request);
        if (token != null) {
            logger.info("TOTP verified successfully for user: {}", request.getUsername());
            return ResponseEntity.ok(token);
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
        String secretKey = authService.generateSecretForUser(usernameOrEmail);
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
