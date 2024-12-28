package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public void addBooking(String tenantName, int houseId) throws SQLException {
        String query = "INSERT INTO bookings (tenant_name, house_id) VALUES (?, ?)";
        try (Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tenantName);
            preparedStatement.setInt(2, houseId);
            preparedStatement.executeUpdate();
        }
    }

    public List<String> getAllBookings() throws SQLException {
        String query = "SELECT * FROM bookings";
        List<String> bookings = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                bookings.add("Tenant: " + resultSet.getString("tenant_name") + ", House ID: "
                        + resultSet.getInt("house_id"));
            }
        }
        return bookings;
    }

    public void updateBooking(String tenantName, int houseId) throws SQLException {
        String query = "UPDATE bookings SET house_id = ? WHERE tenant_name = ?";
        try (Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, houseId);
            preparedStatement.setString(2, tenantName);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteBooking(String tenantName) throws SQLException {
        String query = "DELETE FROM bookings WHERE tenant_name = ?";
        try (Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tenantName);
            preparedStatement.executeUpdate();
        }
    }
}
