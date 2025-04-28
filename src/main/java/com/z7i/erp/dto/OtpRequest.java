package com.z7i.erp.dto;

import jakarta.validation.constraints.NotBlank;

public class OtpRequest {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "OTP is mandatory")
    private String otp;

    public OtpRequest() {}

    public OtpRequest(String username, String otp) {
        this.username = username;
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
