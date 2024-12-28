package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void addUser(String username, String email, String role) throws SQLException {
        String query = "INSERT INTO users (username, email, role) VALUES (?, ?, ?)";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
        }
    }

    public List<String> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        List<String> users = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                users.add("Username: " + resultSet.getString("username") + ", Email: " + resultSet.getString("email") + ", Role: " + resultSet.getString("role"));
            }
        }
        return users;
    }

    public void updateUser(String username, String email, String role) throws SQLException {
        String query = "UPDATE users SET email = ?, role = ? WHERE username = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, role);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE username = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }
    }
}
