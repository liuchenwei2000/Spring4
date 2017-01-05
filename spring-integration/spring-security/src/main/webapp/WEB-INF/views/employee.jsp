<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Your Detail Info</title>
    <base href="${baseUrl}"/>
</head>
<body>
<p>
    Id:${employee.id}
<p>
    Code:${employee.code}
<p>
    Name:${employee.name}
<p>
    Dept:${employee.dept}
<p>
    Salary:${employee.salary}
<p>
<p>
<!--
如果开启了 CSRF 防护的话，只能使用 POST 的方式进行 logout 请求。
如果禁用了 CSRF 防护的话，则可以使用 GET 的方式进行 logout 请求，即超链接形式即可。
-->
<form action="logout" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Logout"/>
</form>
</body>
</html>