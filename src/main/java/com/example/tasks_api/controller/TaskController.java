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
    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> tasks() {
        return taskService.getTaskList();
    }

    @PostMapping("/Task")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task task1 = this.taskService.addTask(task);
        return new ResponseEntity<>(task1, HttpStatus.CREATED);
    }

    @PutMapping("/Task/{id}")
    public String updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        Task result = taskService.updateTask(id, updatedTask);
        return "Updated Task: " + updatedTask.getTaskName();

    }

    @DeleteMapping("/Task/{id}")
    public String deleteTask(@PathVariable int id) {
        Task result = taskService.deleteTask(id);
        return "Deleted Task ID: " + id;
    }

}
