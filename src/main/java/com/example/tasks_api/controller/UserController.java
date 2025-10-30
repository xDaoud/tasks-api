package com.example.tasks_api.controller;

import com.example.tasks_api.model.User;
import com.example.tasks_api.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public User getCurrentUser() {
        int currentUser = getCurrentUserId();
        return userService.findById(currentUser);
    }

    @PutMapping("/me")
    public User updateCurrentUser(@RequestBody User user) {
        int currentUser = getCurrentUserId();
        return userService.updateUser(currentUser, user);
    }

    private int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return user.getUserId();
    }

}
