<%--
  Created by IntelliJ IDEA.
  User: illum
  Date: 26.11.2017
  Time: 2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>GameGround Create Game</title>
</head>
<body>
    <sql:setDataSource var="genres" scope="page" user="root" password="root"
                       driver="com.mysql.cj.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/course?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=Europe/Kiev&useSSL=true"/>
    <sql:query dataSource="${genres}" var="gen"
               sql="SELECT genres.genre FROM genres"/>
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="CreateGame">
        <label for="i1">Title</label><br>
        <input type="text" id = "i1" name="name"><br>
        <label for="i2">Developer</label><br>
        <input type="text" id = "i2" name="developer"><br>
        <label for="i3">Price</label><br>
        <input type="number" id = "i3" name="price" pattern="[0-9]{,3}"><br>
        <label for="i4">Base Discount</label><br>
        <input type="number" id = "i4" name="discount" pattern=""><br>
        <label for="i5">Image</label><br>
        <input type="url" id = "i5" name="image"><br>
        <label for="i6">Year</label><br>
        <input type="number" id = "i6" name="year" pattern="199[0-9]|20[0-2][0-9]"><br>
        <label for="i7">Description</label><br>
        <textarea rows="30" id = "i7" cols="50" name="description"></textarea><br>
        <label for="i8">Genre</label><br>
        <select name="genre" id = "i8"><br>
            <c:forEach items="${gen.rows}" var="el">
                <c:out value="<option value=${el.genre}>${el.genre}</option>" escapeXml="false"/>
            </c:forEach>
        </select>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
