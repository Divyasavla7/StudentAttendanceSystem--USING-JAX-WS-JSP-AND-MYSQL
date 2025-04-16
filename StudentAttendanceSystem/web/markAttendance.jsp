<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mark Attendance</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #74ebd5, #ACB6E5);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background-color: white;
            padding: 30px 40px;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
            max-width: 450px;
            width: 100%;
        }

        h2 {
            margin-bottom: 25px;
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input[type="text"], select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            background-color: #5c67f2;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            margin-top: 20px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #4a54e1;
        }

        .note {
            margin-top: 15px;
            font-size: 13px;
            color: #555;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #333;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Mark Attendance</h2>
    <form action="MarkAttendanceServlet" method="post">
        <label for="rollNo">Roll No:</label>
        <input type="text" name="rollNo" id="rollNo" required>

        <label for="date">Date (YYYY-MM-DD):</label>
        <input type="text" name="date" id="date" placeholder="2025-04-14" required>

        <label for="status">Status:</label>
        <select name="status" id="status" required>
            <option value="">-- Select Status --</option>
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select>

        <input type="submit" value="Submit">
    </form>
    <a class="back-link" href="index.html">← Back to Home</a>
</div>
</body>
</html>
