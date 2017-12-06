<%--
  Created by IntelliJ IDEA.
  User: illum
  Date: 26.11.2017
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>GameGround Game Page</title>
</head>
<body>
    <div>
        <p>${game.name}</p>
        <img src="${game.image}" alt="game picture" width="500" height="500">
        <p>${game.description}</p>
        <table>
            <thead>
            <tr>
                <th>Developer</th>
                <th>Genre</th>
                <th>Year</th>
                <th>Price</th>
                <th>Discount</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${game.developer.developer}</td>
                <td>${game.genre.genre}</td>
                <td>${game.year}</td>
                <td>${game.price}</td>
                <td>${game.discount}</td>
            </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
