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
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tasks");
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                this.taskList.add(new Task(resultSet.getString(2)));
            }
            return new ArrayList<>(taskList);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public Task addTask(Task task) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO tasks(task_name, is_completed) VALUES (?,?)");
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
            throw new RuntimeException(e);
        }
    }

    public Task updateTask(int id, Task task) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE tasks SET task_name = (?) , is_completed = (?) WHERE task_id = (?);");
            Task task1 = new Task(task.getTaskName());
            task1.setId(id);
            task1.setCompleted(task.getCompleted());
            stmt.setString(1, task1.getTaskName());
            stmt.setBoolean(2, task1.getCompleted());
            stmt.setInt(3, id);
            stmt.executeUpdate();
            return task1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in sql: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Task deleteTask(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM tasks WHERE task_id = (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            ResultSet generatedKey = stmt.getGeneratedKeys();
            stmt.executeUpdate();
            while(generatedKey.next()){
                Task task = new Task(generatedKey.getString(2));
                task.setId(generatedKey.getInt(1));
                task.setCompleted(generatedKey.getBoolean(3));
                stmt.executeUpdate();
                return task;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in sql: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
//all data stuff should be here (S in solid)
//and service should depend on this one, inject it there (DI)