package com.z7i.erp.service;

import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

@Service
public class OtpService {

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    public String generateSecretForUser(String username) {
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        return key.getKey();
    }

    public boolean validateOtp(String secret, String otp) {
        int otpInt;
        try {
            otpInt = Integer.parseInt(otp);
        } catch (NumberFormatException e) {
            return false;
        }
        return gAuth.authorize(secret, otpInt);
    }
}
