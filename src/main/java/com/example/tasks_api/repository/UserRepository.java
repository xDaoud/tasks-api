package com.example.tasks_api.repository;

import com.example.tasks_api.model.User;
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
    final
    DataSource dataSource;
    public UserRepository(DataSource dataSource) {
        userList = new ArrayList<>();
        this.dataSource = dataSource;
    }

    public User addUser(User user) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users(user_name, email, password) VALUES(?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            User user1 = new User(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
            stmt.setString(1, user1.getUsername());
            stmt.setString(2, user1.getEmail());
            stmt.setString(3, user1.getPassword());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            while(generatedKeys.next()) {
                int userId = generatedKeys.getInt("user_id");
                user1.setUserId(userId);
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
            User user1 = new User(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
            user1.setUserId(id);
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

    public User findByEmail(String email) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE email = (?)");
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                User user = new User(resultSet.getString("user_name"),resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("role"));
                user.setUserId(resultSet.getInt("user_id"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public User findByUsername(String username) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE user_name = (?)");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                User user = new User(resultSet.getString("user_name"),resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("role"));
                user.setUserId(resultSet.getInt("user_id"));
                return user;
            }
            return null;
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
                User user = new User(resultSet.getString("user_name"),resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("role"));
                user.setUserId(resultSet.getInt("user_id"));
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
                userList.add(new User(resultSet.getString("user_name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("role")));
            }
            return new ArrayList<>(userList);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
