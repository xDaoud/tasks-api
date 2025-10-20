package com.example.tasks_api.service;

import com.example.tasks_api.model.Task;
import com.example.tasks_api.repository.TaskRepository;
import com.example.tasks_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {
    final
    TaskRepository taskRepository;
    final
    UserRepository userRepository;
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {

        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

    public Task addTask(Task task) {
        if(userRepository.getUserById(task.getUserId()) == null) {
            throw new IllegalArgumentException("this is not a registered user");
        }
        return taskRepository.addTask(task);
    }

    public Task updateTask(int id, Task task) {
        return taskRepository.updateTask(id, task);
    }

    public Task deleteTask(int id, int userId) {
        return taskRepository.deleteTask(id, userId);
    }
}
