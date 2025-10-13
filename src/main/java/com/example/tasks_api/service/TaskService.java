package com.example.tasks_api.service;

import com.example.tasks_api.model.Task;

import java.util.*;

public class TaskService {
    private List<Task> taskList;

    public TaskService() {
        taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return new ArrayList<>(taskList);
    }

    public Task addTask(Task task) {
        Task task1 = new Task(task.getTaskName());
        task1.setCompleted(task.getCompleted());
        taskList.add(task1);
        return task1;
    }
}
