<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f1f1f1;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-container {
            background: #fff;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-bottom: 20px;
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            margin-top: 20px;
            width: 100%;
            padding: 10px;
            background-color: #4285f4;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #357ae8;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>Register Student</h2>
    <form method="post" action="RegisterServlet">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required>

        <label for="email">Email:</label>
        <input type="text" name="email" id="email" required>

        <label for="rollNo">Roll No:</label>
        <input type="text" name="rollNo" id="rollNo" required>

        <input type="submit" value="Register">
    </form>
    <a class="back-link" href="index.html">← Back to Home</a>
</div>
</body>
</html>
