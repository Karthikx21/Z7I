package com.z7i.erp.dto;

public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String role;
    private boolean needsQrSetup;

    public UserDto() {}

    public UserDto(Long id, String username, String email, String role, boolean needsQrSetup) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.needsQrSetup = needsQrSetup;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isNeedsQrSetup() {
        return needsQrSetup;
    }

    public void setNeedsQrSetup(boolean needsQrSetup) {
        this.needsQrSetup = needsQrSetup;
    }
}
