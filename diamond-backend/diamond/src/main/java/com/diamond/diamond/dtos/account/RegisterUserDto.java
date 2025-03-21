package com.diamond.diamond.dtos.account;

import java.util.UUID;

import jakarta.validation.constraints.Email;

public class RegisterUserDto {

    @Email
    private String email;
    private String password;
    private String businessName;
    private UUID idempotencyKey;

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setBusinessName(String newName) {
        this.businessName = newName;
    }

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(UUID idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
}
