package services;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response type to HTML
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String rollNo = request.getParameter("rollNo");

        try (PrintWriter out = response.getWriter()) {

            // HTML template start
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<title>Registration Status</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; padding: 20px; }");
            out.println(".container { background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }");
            out.println(".success { color: green; font-weight: bold; }");
            out.println(".error { color: red; font-weight: bold; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='container'>");

            // Basic form validation
            if (name == null || email == null || rollNo == null ||
                name.isEmpty() || email.isEmpty() || rollNo.isEmpty()) {
                out.println("<p class='error'>All fields are required. Please go back and fill in all the details.</p>");
            } else {
                try {
                    // Load JDBC Driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Connect to database
                    try (Connection conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/studentattendancedb?useSSL=false&serverTimezone=UTC",
                            "root",
                            "StarYashvi@1234")) {

                        // Insert query
                        String query = "INSERT INTO Students (name, email, roll_no) VALUES (?, ?, ?)";
                        try (PreparedStatement stmt = conn.prepareStatement(query)) {
                            stmt.setString(1, name);
                            stmt.setString(2, email);
                            stmt.setString(3, rollNo);

                            int result = stmt.executeUpdate();
                            if (result > 0) {
                                out.println("<h2 class='success'>Student registered successfully!</h2>");
                                out.println("<p>Name: " + name + "<br>Email: " + email + "<br>Roll No: " + rollNo + "</p>");
                            } else {
                                out.println("<p class='error'>Something went wrong. Please try again.</p>");
                            }
                        }
                    }
                } catch (Exception ex) {
                    out.println("<p class='error'>Database Error: " + ex.getMessage() + "</p>");
                }
            }

            // Back to form link
            out.println("<br><a href='register.jsp'>Back to Registration Form</a>");

            // HTML template end
            out.println("</div></body></html>");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
