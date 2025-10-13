package com.example.tasks_api.config;

import com.example.tasks_api.service.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {
    @Bean
    public TaskService taskService(){
        return new TaskService();
    }
}
