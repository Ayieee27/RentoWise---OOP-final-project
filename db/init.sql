import java.io.*;
import java.sql.*;

public class RentoWise {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/rentowise"; // Update with your database
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static void main(String[] args) {
        RentoWise app = new RentoWise();

        // Initialize the database
        app.initializeDatabase("resources/init.sql");

        // Start the application
        app.start();
    }

    // Method to execute the SQL script
    private void initializeDatabase(String scriptFilePath) {
        System.out.println("Initializing the database...");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Read SQL file
            StringBuilder sql = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sql.append(line).append("\n");
                }
            }

            // Execute SQL commands
            try (Statement statement = connection.createStatement()) {
                for (String query : sql.toString().split(";")) { // Split commands by semicolon
                    if (!query.trim().isEmpty()) {
                        statement.execute(query.trim());
                        System.out.println("Executed: " + query.trim());
                    }
                }
            }
            System.out.println("Database initialized successfully!");

        } catch (SQLException e) {
            System.err.println("SQL error during database initialization: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading SQL file: " + e.getMessage());
        }
    }

    public void start() {
        // Your existing start logic...
        System.out.println("Welcome to RentoWise!");
    }
}
