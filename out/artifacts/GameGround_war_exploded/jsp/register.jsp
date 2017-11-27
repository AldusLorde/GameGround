<%--
  Created by IntelliJ IDEA.
  User: illum
  Date: 17.11.2017
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="register">
        <label for="tl">Login</label>
        <input id = "tl" type="text" name="name"><br>
        <label for="tp">Password</label>
        <input id = "tp" type="password" name="password"><br>
        <label for="em">Email</label>
        <input id="em" type="email" name="email"><br>
        <label for = "dat">Birth Date</label>
        <input id = "dat" type="date" name="birthDay"><br>
        <input type="submit" value="Register">
    </form>
    <br>
    ${registerError}
</body>
</html>
