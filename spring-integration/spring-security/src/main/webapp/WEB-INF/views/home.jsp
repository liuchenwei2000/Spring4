<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>"/>
    <title>Spring Security</title>
</head>
<body>
Welcome here.
<p>
    <a href="employee/me">/employee/me</a>
<p>
    <a href="employee/register">/employee/register</a>
</body>
</html>