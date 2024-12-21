import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/RentoWise"; // Replace 'rentowise' with your database name
    private static final String DB_USER = "root"; // Replace with your database username
    private static final String DB_PASSWORD = "Meowaye"; // Replace with your database password

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Step 1: Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the database successfully!");

            // Step 3: Perform operations (Example: Add a house and retrieve all houses)
            addHouse(connection, "789 Pine St", 2000.00);
            retrieveHouses(connection);

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            // Step 4: Close the connection
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    private static void addHouse(Connection connection, String address, double rentAmount) {
        String query = "INSERT INTO houses (address, rent_amount) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, address);
            preparedStatement.setDouble(2, rentAmount);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " house(s) added.");
        } catch (SQLException e) {
            System.out.println("Error adding house: " + e.getMessage());
        }
    }

    private static void retrieveHouses(Connection connection) {
        String query = "SELECT * FROM houses";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("\n--- House Details ---");
            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                String address = resultSet.getString("address");
                double rentAmount = resultSet.getDouble("rent_amount");
                System.out.println("ID: " + houseId + ", Address: " + address + ", Rent: " + rentAmount);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving houses: " + e.getMessage());
        }
    }
}