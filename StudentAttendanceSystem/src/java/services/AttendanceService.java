package services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.*;

@WebService
public class AttendanceService {
    @WebMethod
    public String markAttendance(String rollNo, String date, String status) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentattendancedb?useSSL=false&serverTimezone=UTC", "root", "StarYashvi@1234");
            String studentQuery = "SELECT id FROM Students WHERE roll_no = ?";
            PreparedStatement stmt = conn.prepareStatement(studentQuery);
            stmt.setString(1, rollNo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int studentId = rs.getInt("id");
                String query = "INSERT INTO Attendance (student_id, date, status) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(query);
                insertStmt.setInt(1, studentId);
                insertStmt.setString(2, date);
                insertStmt.setString(3, status);
                insertStmt.executeUpdate();
                conn.close();
                return "Attendance marked successfully.";
            } else {
                return "Student not found.";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}