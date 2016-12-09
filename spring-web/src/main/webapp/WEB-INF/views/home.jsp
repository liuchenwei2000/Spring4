<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>"/>
    <title>spring-web</title>
</head>
<body>
<img src="resources/image/cross.jpg">
Welcome to SpringMVC
<p>
    <a href="books">/books</a>
<p>
    <a href="users?total=3">/users?total=3</a>
<p>
    <a href="user/12345">/user/12345</a>
<p>
    <a href="user/register">/user/register</a>
<p>
    <a href="contact/register">/contact/register</a>
</body>
</html>