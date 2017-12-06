<%--
  Created by IntelliJ IDEA.
  User: illum
  Date: 29.11.2017
  Time: 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>GameGround User Page</title>
</head>
<body>
    <p>User's name = ${userL.name}</p>
    <c:if test="${userL.role eq 1 or userL.role eq 2}">
        <p>User's id = ${userL.id}</p>
        <p>User's role = ${userL.role}</p>
        <p>User's password = ${userL.password}</p>
    </c:if>
    <p>User's email = ${userL.email}</p>
    <p>User's birthday = ${userL.birthDay}</p>
    <p>User's last activity = ${userL.lastActivity}</p>
    <p>User's registration time = ${userL.creationTime}</p>
    <c:if test="${user.id eq userL.id}">
        <a href="/controller?command=showOrderBox">See order box.</a>
    </c:if>
</body>
</html>
