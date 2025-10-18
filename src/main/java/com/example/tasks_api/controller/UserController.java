package com.example.tasks_api.controller;

import com.example.tasks_api.model.User;
import com.example.tasks_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {return userService.findAll();}

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 = userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public  String updateUser(User user,@PathVariable int id) {
        User result = userService.updateUser(id, user);
        return "Updated user: " + user.getUsername();
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id) {
        User result = userService.deleteTask(id);
        return "Deleted user: " + id;
    }
}
