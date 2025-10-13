package com.example.tasks_api.controller;

import com.example.tasks_api.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.tasks_api.service.TaskService;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService = new TaskService();
    @GetMapping("/Greetings")
    public String Greetings(){
        return "Hello world<br>Trying to learn Spring boot<br>To become employable";
    }

    @GetMapping("/tasks")
    public List<Task> tasks() {
        return taskService.getTaskList();
    }

    @PostMapping(value = "/addTask")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task task1 = this.taskService.addTask(task);
        return new ResponseEntity<>(task1, HttpStatus.CREATED);
    }

}
