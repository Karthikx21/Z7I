package com.z7i.erp.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String mobile;

    private String email;

    @Column(name = "totp_secret")
    private String totpSecret;

    @Column(nullable = false)
    private String role;

    @Transient
    private boolean needsQrSetup;

    @Transient
    private String tempTotpSecret;

    public String getTempTotpSecret() {
        return tempTotpSecret;
    }

    public void setTempTotpSecret(String tempTotpSecret) {
        this.tempTotpSecret = tempTotpSecret;
    }

    // Constructors
    public Users() {}

    public Users(String username, String password, String mobile, String email, String totpSecret, String role) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.totpSecret = totpSecret;
        this.role = role;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTotpSecret() {
        return totpSecret;
    }

    public void setTotpSecret(String totpSecret) {
        this.totpSecret = totpSecret;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isNeedsQrSetup() {
        return this.totpSecret == null || this.totpSecret.isEmpty();
    }

    // UserDetails interface methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return java.util.Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
