<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Employee</title>
</head>
<body>

<form method="POST">
    Code:<input type="text" name="code"/>
    <p>Name:<input type="text" name="name"/>
    <p>Password:<input type="password" name="password"/>
    <p>Dept:<input type="text" name="dept"/>
    <p>Salary:<input type="number" min="0" max="99999" name="salary"/>
    <!-- 下面的域是为了处理 Spring Security 的 CSRF 防护 -->
    <p><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p>
    <input type="submit" value="Save"/>
</form>
</body>
</html>