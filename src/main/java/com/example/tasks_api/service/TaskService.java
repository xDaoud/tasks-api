package com.example.tasks_api.service;

import com.example.tasks_api.model.Task;
import com.example.tasks_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    public TaskService() {

    }

    public List<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

    public Task addTask(Task task) {
        return taskRepository.addTask(task);
    }

    public Task updateTask(int id, Task task) {
        return taskRepository.updateTask(id, task);
    }

    public Task deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }
}
