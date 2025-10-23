package com.example.tasks_api.model;

import java.time.LocalDateTime;

public class User {
    private int userID;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private String passwordHash;
    private String role = "USER";

    public User(String username, String email, String passwordHash, String role) {
        this.createdAt = LocalDateTime.now();
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    public String getRole() {
        return role;
    }
}
