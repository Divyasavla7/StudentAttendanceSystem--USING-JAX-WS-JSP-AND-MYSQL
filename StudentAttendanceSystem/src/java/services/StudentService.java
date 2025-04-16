package services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.*;

@WebService
public class StudentService {
    @WebMethod
    public String registerStudent(String name, String email, String rollNo) {
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentattendancedb?useSSL=false&serverTimezone=UTC", "root", "StarYashvi@1234");
            String query = "INSERT INTO Students (name, email, roll_no) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, rollNo);
            stmt.executeUpdate();
            conn.close();
            return "Student registered successfully.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}