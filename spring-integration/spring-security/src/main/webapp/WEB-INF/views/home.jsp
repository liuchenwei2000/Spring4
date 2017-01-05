<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${baseUrl}" />
    <title>Employee System</title>
</head>
<body>
Welcome here.
<p>
    <a href="employee/me">个人信息</a>
<p>
    <a href="employee/register">注册新员工</a>
<p>
    <%--
    应用没有对 employee/all 做 Web 层的保护，使得任何用户都可以访问，
    但在 DAO 层做了对 bean 方法的保护，因此没有权限的用户最终还是看不到所有员工信息。
    --%>
    <a href="employee/all">所有员工</a>
</body>
</html>