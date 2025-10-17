package com.example.tasks_api.model;

import java.time.LocalDateTime;

public class User {
    private int userID;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    public User(String username, String email) {
        this.createdAt = LocalDateTime.now();
        this.email = email;
        this.username = username;
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
}
