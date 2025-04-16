<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>View Attendance Report</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f9f9f9;
            padding: 40px;
        }
        form {
            background-color: #fff;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            max-width: 500px;
            margin: auto;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin: 12px 0 5px;
        }
        input, select {
            width: 100%;
            padding: 10px;
            border-radius: 8px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            margin-top: 20px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<h2>View Attendance Report</h2>
<form action="ViewReportServlet" method="post">
    <label>Roll No (optional):</label>
    <input type="text" name="rollNo">

    <label>From Date (yyyy-mm-dd):</label>
    <input type="text" name="fromDate">

    <label>To Date (yyyy-mm-dd):</label>
    <input type="text" name="toDate">

    <input type="submit" value="View Report">
</form>
        <a class="back-link" href="index.html">← Back to Home</a>
</body>
</html>
