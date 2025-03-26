package com.diamond.diamond.dtos.account;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class RegisterUserDto {

    @Email
    private String email;
    private String name;
    @Size(min=2, max=50)
    private String businessName;
    private UUID idempotencyKey;

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setName(String newName) {
        this.name = newName;
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
