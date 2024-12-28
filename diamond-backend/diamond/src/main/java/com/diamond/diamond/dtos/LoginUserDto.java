/*
 * todo: add input validation to prevent attacks
 */
package com.diamond.diamond.dtos;

public class LoginUserDto {

    private String email;
    private String password;

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

}
