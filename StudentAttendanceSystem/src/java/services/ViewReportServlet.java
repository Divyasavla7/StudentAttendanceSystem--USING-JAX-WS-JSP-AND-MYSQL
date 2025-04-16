package services;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ViewReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rollNo = request.getParameter("rollNo");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentattendancedb?useSSL=false&serverTimezone=UTC",
                "root", "StarYashvi@1234");

            StringBuilder query = new StringBuilder(
                "SELECT s.name, s.roll_no, a.date, a.status FROM Students s " +
                "JOIN Attendance a ON s.id = a.student_id WHERE 1=1");

            List<String> params = new ArrayList<>();
            if (rollNo != null && !rollNo.trim().isEmpty()) {
                query.append(" AND s.roll_no = ?");
                params.add(rollNo);
            }
            if (fromDate != null && !fromDate.trim().isEmpty()) {
                query.append(" AND a.date >= ?");
                params.add(fromDate);
            }
            if (toDate != null && !toDate.trim().isEmpty()) {
                query.append(" AND a.date <= ?");
                params.add(toDate);
            }

            query.append(" ORDER BY a.date DESC");

            PreparedStatement stmt = conn.prepareStatement(query.toString());
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            out.println("<html><head><title>Attendance Report</title></head><body>");
            out.println("<h2>Attendance Report</h2>");
            out.println("<table border='1' cellpadding='10'><tr><th>Name</th><th>Roll No</th><th>Date</th><th>Status</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("roll_no") + "</td>");
                out.println("<td>" + rs.getString("date") + "</td>");
                out.println("<td>" + rs.getString("status") + "</td></tr>");
            }
            out.println("</table>");
            out.println("<br><a href='viewReport.jsp'>Go Back</a>");
            out.println("</body></html>");

            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
