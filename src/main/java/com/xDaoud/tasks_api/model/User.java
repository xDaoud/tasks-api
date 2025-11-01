package com.xDaoud.tasks_api.model;

import java.time.LocalDateTime;

public class User {
    private int userID;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private String password;
    private String role = "USER";

    public User(String username, String email, String password, String role) {
        this.createdAt = LocalDateTime.now();
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getUserId() {
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

    public void setUserId(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
}
