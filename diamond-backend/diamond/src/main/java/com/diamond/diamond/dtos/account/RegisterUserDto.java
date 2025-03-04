package com.diamond.diamond.dtos.account;

import jakarta.validation.constraints.Email;

public class RegisterUserDto {

    @Email
    private String email;
    private String password;
    private String businessName;

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
}
