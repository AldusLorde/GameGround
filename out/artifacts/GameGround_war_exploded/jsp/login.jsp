<%--
  Created by IntelliJ IDEA.
  User: illum
  Date: 15.11.2017
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GameGround LogIn</title>
</head>
<body>
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="login">
        <p>Login</p> <input type="text" name="name"> <br><br>
        <p>Password</p> <input type="password" name="password"> <br><br>
        <input type="submit" value="Log In">
        <br>
        ${wrongAction}
        <br>
        ${errorLoginPassMessage}
        <br>
        ${nullPage}
        <br>
        <hr>
    </form>
    <form action="/controller" method="Post">
        <input type="hidden" name="command" value="toCreation">
        <input type="submit" value="Create Account">
    </form>
</body>
</html>
