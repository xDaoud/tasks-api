package com.example.tasks_api.repository;

import com.example.tasks_api.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
@Repository
public class TaskRepository {
    private final List<Task> taskList;
    @Autowired
    private DataSource dataSource;
    public TaskRepository() {
        taskList = new ArrayList<>();
    }


    public List<Task> getTaskList() {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM task");
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                this.taskList.add(new Task(resultSet.getNString(2)));
            }
            return new ArrayList<>(taskList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Task addTask(Task task) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO task(task_name, is_completed) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            Task task1 = new Task(task.getTaskName());
            task1.setCompleted(task.getCompleted());
            stmt.setString(1, task1.getTaskName());
            stmt.setBoolean(2, task1.getCompleted());
            ResultSet generatedKey = stmt.getGeneratedKeys();
            while(generatedKey.next()){
                task1.setId(generatedKey.getInt(1));
            }
            stmt.executeUpdate();
            return task1;
        } catch (SQLException e){
            System.out.println("an error making SQL connection: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
//all data stuff should be here (S in solid)
//and service should depend on this one, inject it there (DI)