package com.diamond.diamond.dtos;

public class RegisterUserDto {

    private String email;
    private String password;
    private String fullName;

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setFullName(String newFullName) {
        this.fullName = newFullName;
    }
}
