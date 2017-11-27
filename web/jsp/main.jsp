<%--
  Created by IntelliJ IDEA.
  User: illum
  Date: 16.11.2017
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GameGround</title>
</head>
<body>
    <h3>Hello, ${user.name}</h3>
    <form action="/controller" method="post">
        <input type="hidden" value="logout" name="command">
        <input type="submit" value="log out">
    </form>
    <br>
    <form action="/controller" method="post">
        <input type="hidden" value="showusers" name="command">
        <input type="submit" value="Show Users">
    </form>
</body>
</html>
