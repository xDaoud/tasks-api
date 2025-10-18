package com.example.tasks_api.service;

import com.example.tasks_api.model.User;
import com.example.tasks_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll() { return userRepository.getUserList();}

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public User updateUser(int id, User user) {
        return userRepository.updateUser(id, user);
    }

    public User deleteTask(int id) {
        return userRepository.deleteUser(id);
    }

}
