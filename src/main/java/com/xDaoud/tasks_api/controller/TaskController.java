package com.xDaoud.tasks_api.controller;

import com.xDaoud.tasks_api.model.Task;
import com.xDaoud.tasks_api.model.User;
import com.xDaoud.tasks_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.xDaoud.tasks_api.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    final
    TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public List<Task> tasks() {
        return taskService.getTasksbyUserId(getCurrentUserId());
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        int currentUserId = getCurrentUserId();
        task.setUserId(currentUserId);
        Task task1 = this.taskService.addTask(task);
        return new ResponseEntity<>(task1, HttpStatus.CREATED);
    }

    @PutMapping("/taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable int taskId, @RequestBody Task updatedTask) {
        Task result = taskService.updateTask(taskId, updatedTask, getCurrentUserId());
        return ResponseEntity.ok(result);

    }

    @DeleteMapping("/taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId, getCurrentUserId());
        return ResponseEntity.noContent().build();
    }

    private int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return user.getUserId();
    }

}
