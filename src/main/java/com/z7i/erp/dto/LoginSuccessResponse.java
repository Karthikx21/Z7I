package com.z7i.erp.dto;

public class LoginSuccessResponse {

    private String status;
    private String token;
    private UserDto user;

    public LoginSuccessResponse() {}

    public LoginSuccessResponse(String status, String token, UserDto user) {
        this.status = status;
        this.token = token;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
