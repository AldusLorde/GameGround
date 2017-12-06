<%--
  Created by IntelliJ IDEA.
  User: illum
  Date: 27.11.2017
  Time: 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Game Ground Games</title>
</head>
<body>
    <table>
        <thead>
        <tr>
            <th>Picture</th>
            <th>Title</th>
            <th>Developer</th>
            <th>Genre</th>
            <th>Year</th>
            <th>Price</th>
            <th>Discount</th>
            <c:if test="${(pageContext.session.getAttribute('user').role eq 1)or(pageContext.session.getAttribute('user').role eq 2)}">
                <th>Id</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="el" items="${gamesList}">
                <tr>
                    <td>
                        <img src="${el.image}">
                    </td>
                    <td>
                        <a href="/controller?command=showGame&id=${el.id}">${el.name}</a>
                    </td>
                    <td>${el.developer.developer}</td>
                    <td>${el.genre.genre}</td>
                    <td>${el.year}</td>
                    <td>${el.price}</td>
                    <td>${el.discount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
