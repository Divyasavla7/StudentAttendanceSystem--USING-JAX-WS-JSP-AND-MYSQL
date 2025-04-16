package services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.*;

@WebService
public class ReportService {
    @WebMethod
    public String getAttendanceReport(String rollNo) {
        StringBuilder report = new StringBuilder();
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentattendancedb?useSSL=false&serverTimezone=UTC", "root", "StarYashvi@1234");
            String studentQuery = "SELECT id FROM Students WHERE roll_no = ?";
            PreparedStatement stmt = conn.prepareStatement(studentQuery);
            stmt.setString(1, rollNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int studentId = rs.getInt("id");
                String reportQuery = "SELECT date, status FROM Attendance WHERE student_id = ?";
                PreparedStatement reportStmt = conn.prepareStatement(reportQuery);
                reportStmt.setInt(1, studentId);
                ResultSet reportRs = reportStmt.executeQuery();
                while (reportRs.next()) {
                    report.append("Date: ").append(reportRs.getDate("date"))
                          .append(", Status: ").append(reportRs.getString("status")).append("\n");
                }
                conn.close();
                return report.toString();
            } else {
                return "Student not found.";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}