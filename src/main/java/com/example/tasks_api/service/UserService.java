package com.example.tasks_api.service;

import com.example.tasks_api.model.User;
import com.example.tasks_api.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,  BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null || userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalStateException("User already exists");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        if( user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        userRepository.addUser(user);
    }

    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if(user == null) {
            return false;
        }

        return passwordEncoder.matches(password, user.getPassword());
    }

    public List<User> findAll() { return userRepository.getUserList();}

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public User updateUser(int id, User user) {
        return userRepository.updateUser(id, user);
    }

    public User deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
