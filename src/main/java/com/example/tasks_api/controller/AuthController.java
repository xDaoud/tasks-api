package com.example.tasks_api.controller;

import com.example.tasks_api.model.LoginRequest;
import com.example.tasks_api.model.User;
import com.example.tasks_api.service.CustomUserDetailsService;
import com.example.tasks_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final UserService userService;
    final CustomUserDetailsService customUserDetailsService;
    public AuthController(UserService userService,  CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("is it even in???");
            boolean success = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
            if (success) {
                System.out.println("Checking credentials");
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());
                System.out.println("userDetails = " + userDetails);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                System.out.println(authenticationToken + ": authenticationToken = " + authenticationToken.getDetails());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println("userDetails2 = " + userDetails);
                return ResponseEntity.ok("success, session created");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized, wrong username or password");
            }
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
