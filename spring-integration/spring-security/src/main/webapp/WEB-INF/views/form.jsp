<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>"/>
    <title>Add Employee</title>
</head>
<body>

<form action="employee/save" method="POST">
    Code:<input type="text" name="code"/>
    <p>Name:<input type="text" name="name"/>
    <p>Password:<input type="password" name="password"/>
    <p>Dept:<input type="text" name="dept"/>
    <p>Salary:<input type="number" min="0" max="99999" name="salary"/>
    <p>
    <input type="submit" value="Save"/>
</form>
</body>
</html>