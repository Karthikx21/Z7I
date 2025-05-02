package com.z7i.erp.service;

import java.util.Optional;

import com.z7i.erp.dto.ForgotPasswordRequest;
import com.z7i.erp.dto.LoginRequest;
import com.z7i.erp.dto.LoginSuccessResponse;
import com.z7i.erp.dto.OtpRequest;
import com.z7i.erp.model.Users;

public interface AuthServiceInterface {

    Optional<Users> authenticate(String usernameOrEmail, String password);

    LoginSuccessResponse login(LoginRequest request);

    boolean resetPassword(ForgotPasswordRequest request);

    boolean validateTotp(String username, String otp);

    boolean saveTotpSecretForUser(String username);

    Optional<Users> getUserByUsername(String username);

    String generateSecretForUser(String usernameOrEmail);

    String verifyTotp(OtpRequest request);

    boolean assignRoleToUser(String username, String roleName);

    boolean removeRoleFromUser(String username, String roleName);
}
