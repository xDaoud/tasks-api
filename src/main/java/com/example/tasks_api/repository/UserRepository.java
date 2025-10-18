package com.example.tasks_api.repository;

import com.example.tasks_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class UserRepository {
    List<User> userList;
    @Autowired
    DataSource dataSource;
    public UserRepository() {
        userList = new ArrayList<>();
    }

    public User addUser(User user) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users(user_name, email) VALUES(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            User user1 = new User(user.getUsername(), user.getEmail());
            stmt.setString(1, user1.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            while(generatedKeys.next()) {
                int userId = generatedKeys.getInt("user_id");
                user1.setUserID(userId);
            }
            return user1;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public User updateUser(int id,User user) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET user_name = (?) , email = (?) WHERE user_id = (?);");
            User user1 = new User(user.getUsername(), user.getEmail());
            user1.setUserID(id);
            user1.setCreatedAt(user.getCreatedAt());
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, id);
            stmt.executeUpdate();
            return user1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public User deleteUser(int id) {
        try(Connection connection = dataSource.getConnection()) {
            User toBeDeleted = getUserById(id);
            if(toBeDeleted != null) {
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE user_id = (?)");
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
            return toBeDeleted;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public User getUserById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE user_id = (?)");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                User user = new User(resultSet.getString("user_name"),resultSet.getString("email"));
                user.setUserID(resultSet.getInt("user_id"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<User> getUserList() {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                userList.add(new User(resultSet.getString("user_name"), resultSet.getString("email")));
            }
            return new ArrayList<>(userList);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
