package com.xDaoud.tasks_api.model;

public class Task {
    private int id;
    private String  taskName;
    private Boolean isCompleted;
    private int userId;
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
