package com.example.tasks_api.model;

public class LoginRequest {
    private String username, hashedPassowrd;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHashedPassowrd() {
        return hashedPassowrd;
    }
    public void setHashedPassowrd(String hashedPassowrd) {
        this.hashedPassowrd = hashedPassowrd;
    }
}
