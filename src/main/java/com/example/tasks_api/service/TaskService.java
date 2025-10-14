package com.example.tasks_api.service;

import com.example.tasks_api.model.Task;
import com.example.tasks_api.repository.TaskRepository;

import java.util.*;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();
    public TaskService() {

    }

    public List<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

    public Task addTask(Task task) {
        Task task1 = new Task(task.getTaskName());
        task1.setCompleted(task.getCompleted());
        task1.setId(this.autoId++);
        taskList.add(task1);
        return task1;
    }
}
