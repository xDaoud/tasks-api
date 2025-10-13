package com.example.tasks_api.model;

public class Task {
    private String  taskName;
    private Boolean isCompleted;
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
}
