/*
 * Defines a class to represent the response data for a user's login request 
 */
package com.diamond.diamond.responses;

public class LoginResponse {

    private String accessToken;
    private String refreshToken;

    private long accessExpiresIn;
    private long refreshExpiresIn;

    public LoginResponse(String accessToken, String refreshToken, long accessExpiresIn, long refreshExpiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessExpiresIn = accessExpiresIn;
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getAccessExpiresIn() {
        return accessExpiresIn;
    }

    public long getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setAccessToken(String newAccessToken) {
        this.accessToken = newAccessToken;
    }

    public void setRefreshToken(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
    }

    public void setAccessExpiresIn(long accessExpiresIn) {
        this.accessExpiresIn = accessExpiresIn;
    }

    public void setRefreshExpiresIn(long refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

}
