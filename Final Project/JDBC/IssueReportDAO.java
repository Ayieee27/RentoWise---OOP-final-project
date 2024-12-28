package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueReportDAO {
    public void addIssueReport(String reporterName, String email, String description) throws SQLException {
        String query = "INSERT INTO issue_reports (reporter_name, email, description) VALUES (?, ?, ?)";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, reporterName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, description);
            preparedStatement.executeUpdate();
        }
    }

    public List<String> getAllIssueReports() throws SQLException {
        String query = "SELECT * FROM issue_reports";
        List<String> reports = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                reports.add("Reporter: " + resultSet.getString("reporter_name") + ", Email: " + resultSet.getString("email") + ", Description: " + resultSet.getString("description"));
            }
        }
        return reports;
    }

    public void updateIssueReport(int reportId, String description) throws SQLException {
        String query = "UPDATE issue_reports SET description = ? WHERE report_id = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, reportId);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteIssueReport(int reportId) throws SQLException {
        String query = "DELETE FROM issue_reports WHERE report_id = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, reportId);
            preparedStatement.executeUpdate();
        }
    }
}
