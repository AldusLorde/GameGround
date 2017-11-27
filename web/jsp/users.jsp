<%--
  Created by IntelliJ IDEA.
  User: illum
  Date: 18.11.2017
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>GameGround Users</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Password</th>
                <th>Role</th>
                <th>Email</th>
                <th>Date of the registration</th>
                <th>Last Activity</th>
                <th>Birth Day</th>
            </tr>
        </thead>
        <tbody>
           <c:forEach items="${users.rows}" var="user">
               <tr>
                   <td>${user.id}</td>
                   <td>${user.name}</td>
                   <td>${user.password}</td>
                   <td>${user.role}</td>
                   <td>${user.email}</td>
                   <td>${user.creationTime}</td>
                   <td>${user.lastActivity}</td>
                   <td>${user.birthDay}</td>
                   <td>
                       <form action="/controller" method="post">
                           <input type="hidden" name="command" value="deleteUser">
                           <input type="hidden" name="id" value="${user.id}">
                           <input type="submit" value="Delete">
                       </form>
                   </td>
               </tr>
           </c:forEach>
        </tbody>
    </table>
</body>
</html>
