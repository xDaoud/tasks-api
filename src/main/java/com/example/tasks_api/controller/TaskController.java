package com.example.tasks_api.controller;

import com.example.tasks_api.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.tasks_api.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    final
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/notyet")
    public List<Task> tasks() {
        return taskService.getTaskList();
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        System.out.println("user id" + task.getUserId());
        task.setUserId(2);
        System.out.println("user id2" + task.getUserId());
        Task task1 = this.taskService.addTask(task);
        System.out.println("user id3" + task1.getUserId());
        return new ResponseEntity<>(task1, HttpStatus.CREATED);
    }

    @PutMapping("/Task/{id}")
    public String updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        Task result = taskService.updateTask(id, updatedTask);
        return "Updated Task: " + updatedTask.getTaskName();

    }

    @DeleteMapping("/Task/{id}/user/{userId}")
    public String deleteTask(@PathVariable int id, @PathVariable int userId) {
        Task result = taskService.deleteTask(id, userId);
        return "Deleted Task ID: " + id;
    }

}
