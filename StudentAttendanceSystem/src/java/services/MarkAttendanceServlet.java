package services;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MarkAttendanceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String rollNo = request.getParameter("rollNo");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentattendancedb?useSSL=false&serverTimezone=UTC", 
                "root", "StarYashvi@1234");

            // Find student ID from roll number
            String studentQuery = "SELECT id, name FROM Students WHERE roll_no = ?";
            PreparedStatement stmt = conn.prepareStatement(studentQuery);
            stmt.setString(1, rollNo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int studentId = rs.getInt("id");

                // Insert attendance
                String query = "INSERT INTO Attendance (student_id, date, status) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(query);
                insertStmt.setInt(1, studentId);
                insertStmt.setString(2, date);
                insertStmt.setString(3, status);
                insertStmt.executeUpdate();

                // Display all attendance for this student
                out.println("<html><head><title>Attendance Summary</title>");
                out.println("<style>");
                out.println("body { font-family: Arial; background: #f0f2f5; padding: 30px; }");
                out.println("table { width: 60%; margin: auto; border-collapse: collapse; }");
                out.println("th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }");
                out.println("th { background-color: #4CAF50; color: white; }");
                out.println("h2 { text-align: center; color: #333; }");
                out.println("a { display: block; text-align: center; margin-top: 20px; }");
                out.println("</style></head><body>");

                out.println("<h2>Attendance Summary for " + rs.getString("name") + " (" + rollNo + ")</h2>");
                out.println("<table>");
                out.println("<tr><th>Date</th><th>Status</th></tr>");

                String fetchAttendance = "SELECT date, status FROM Attendance WHERE student_id = ? ORDER BY date DESC";
                PreparedStatement fetchStmt = conn.prepareStatement(fetchAttendance);
                fetchStmt.setInt(1, studentId);
                ResultSet attRs = fetchStmt.executeQuery();

                while (attRs.next()) {
                    out.println("<tr><td>" + attRs.getString("date") + "</td><td>" + attRs.getString("status") + "</td></tr>");
                }

                out.println("</table>");
                out.println("<a href='markAttendance.jsp'>← Mark Another Attendance</a>");
                out.println("</body></html>");

                conn.close();
            } else {
                out.println("Student not found.");
            }

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
