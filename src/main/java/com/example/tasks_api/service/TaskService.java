package com.example.tasks_api.service;

import com.example.tasks_api.model.Task;
import com.example.tasks_api.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    final
    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksbyUserId(int userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task addTask(Task task) {
        return taskRepository.addTask(task);
    }

    public Task updateTask(int taskId, Task task, int userId) {
        Task existing = taskRepository.findByIdUserId(taskId, userId);
        if(existing == null) {
            throw new IllegalArgumentException("Task doesn't exist");
        }
        return taskRepository.updateTask(taskId, task, userId);
    }

    public void deleteTask(int taskId, int userId) {
        Task existing = taskRepository.findByIdUserId(taskId, userId);
        if(existing == null) {
            throw new IllegalArgumentException("Task doesn't exist");
        }
        taskRepository.deleteTask(taskId, userId);
    }
}
