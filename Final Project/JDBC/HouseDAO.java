package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseDAO {
    public void addHouse(int houseId, String address, double rentAmount) throws SQLException {
        String query = "INSERT INTO houses (house_id, address, rent_amount) VALUES (?, ?, ?)";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, houseId);
            preparedStatement.setString(2, address);
            preparedStatement.setDouble(3, rentAmount);
            preparedStatement.executeUpdate();
        }
    }

    public List<String> getAllHouses() throws SQLException {
        String query = "SELECT * FROM houses";
        List<String> houses = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                houses.add("ID: " + resultSet.getInt("house_id") + ", Address: " + resultSet.getString("address") + ", Rent: " + resultSet.getDouble("rent_amount"));
            }
        }
        return houses;
    }

    public void updateHouse(int houseId, String address, double rentAmount) throws SQLException {
        String query = "UPDATE houses SET address = ?, rent_amount = ? WHERE house_id = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, address);
            preparedStatement.setDouble(2, rentAmount);
            preparedStatement.setInt(3, houseId);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteHouse(int houseId) throws SQLException {
        String query = "DELETE FROM houses WHERE house_id = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, houseId);
            preparedStatement.executeUpdate();
        }
    }
}
