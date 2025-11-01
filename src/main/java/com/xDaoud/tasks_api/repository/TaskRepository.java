package com.xDaoud.tasks_api.repository;

import com.xDaoud.tasks_api.model.Task;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
@Repository
public class TaskRepository {
    private final List<Task> taskList;
    private final DataSource dataSource;
    public TaskRepository(DataSource dataSource) {
        taskList = new ArrayList<>();
        this.dataSource = dataSource;
    }


    public List<Task> findByUserId(int userId) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tasks WHERE user_id = ?");
            stmt.setInt(1, userId);
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
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO tasks(task_name, is_completed, user_id) VALUES (?,?,?)");
            Task task1 = new Task(task.getTaskName());
            task1.setCompleted(task.getCompleted());
            task1.setUserId(task.getUserId());
            stmt.setString(1, task1.getTaskName());
            stmt.setBoolean(2, task1.getCompleted());
            stmt.setInt(3, task1.getUserId());
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

    public Task updateTask(int id, Task task, int userId) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE tasks SET task_name = (?) , is_completed = (?) WHERE task_id = (?) AND user_id = (?);");
            Task task1 = new Task(task.getTaskName());
            task1.setId(id);
            task1.setUserId(task.getUserId());
            task1.setCompleted(task.getCompleted());
            stmt.setString(1, task1.getTaskName());
            stmt.setBoolean(2, task1.getCompleted());
            stmt.setInt(3, id);
            stmt.setInt(4, userId);
            stmt.executeUpdate();
            return task1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in sql: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Task deleteTask(int id, int userId) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM tasks WHERE task_id = (?) AND user_id = (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.setInt(2, userId);
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

    public Task findByIdUserId(int id, int  userId) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tasks WHERE task_id = (?) AND user_id = (?)");
            stmt.setInt(1, id);
            stmt.setInt(2, userId);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                return new Task(resultSet.getString(2));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in sql: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
