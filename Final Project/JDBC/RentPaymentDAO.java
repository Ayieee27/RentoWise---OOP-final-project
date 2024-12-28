package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentPaymentDAO {
    public void addRentPayment(String name, String cardNumber, double amount) throws SQLException {
        String query = "INSERT INTO rent_payments (name, card_number, amount) VALUES (?, ?, ?)";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setDouble(3, amount);
            preparedStatement.executeUpdate();
        }
    }

    public List<String> getAllRentPayments() throws SQLException {
        String query = "SELECT * FROM rent_payments";
        List<String> payments = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                payments.add("Name: " + resultSet.getString("name") + ", Card Number: " + resultSet.getString("card_number") + ", Amount: " + resultSet.getDouble("amount"));
            }
        }
        return payments;
    }

    public void updateRentPayment(String name, String cardNumber, double amount) throws SQLException {
        String query = "UPDATE rent_payments SET card_number = ?, amount = ? WHERE name = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteRentPayment(String name) throws SQLException {
        String query = "DELETE FROM rent_payments WHERE name = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }
}
