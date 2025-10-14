package com.example.tasks_api.repository;

import com.example.tasks_api.model.Task;

import java.sql.*;
import java.util.*;
class SQLConnection {
    Connection connection;
    SQLConnection() throws SQLException {
        String connStr = "jdbc:postgresql:tasks?user=myuser&password=mypassword";
        try
        {
            connection = DriverManager.getConnection(connStr);
            PreparedStatement stmt = connection.prepareStatement("CREATE TABLE IF NOT EXIST task(task_id SERIAL NOT NULL PRIMARY KEY, task_name VARCHAR(255)) NOT NULL UNIQUE, is_completed BOOLEAN NOT NULL");
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("a problem in making SQL connection " + e.getMessage());;
        } finally {
            connection.close();
        }
    }
}
public class TaskRepository {
    private List<Task> taskList;
    private int autoId;
    SQLConnection sqlConnection;
    public TaskRepository() {
        taskList = new ArrayList<>();
        try {
            sqlConnection = new SQLConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getTaskList() {
        try{
            PreparedStatement stmt = sqlConnection.connection.prepareStatement("SELECT * FROM task");
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                this.taskList.add(new Task(resultSet.getNString(2)));
            }
            return new ArrayList<>(taskList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
//all data stuff should be here (S in solid)
//and service should depend on this one, inject it there (DI)