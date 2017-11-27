<%--
  Created by IntelliJ IDEA.
  User: illum
  Date: 16.11.2017
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>GameGround Error Page</title>
</head>
<body>
    <p>Request From ${pageContext.errorData.requestURI} is failed</p>
    <p>Servlet name or type: ${pageContext.errorData.servletName}</p>
    <p>Status code: ${pageContext.errorData.statusCode}</p>
    <p>Exception: ${pageContext.errorData.throwable}</p>
</body>
</html>
