package com.xDaoud.tasks_api.controller;

import com.xDaoud.tasks_api.model.User;
import com.xDaoud.tasks_api.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {
    final UserService userService;
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}
