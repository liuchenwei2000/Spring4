<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${baseUrl}"/>
    <title>Employee List</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Code</th>
        <th>Name</th>
        <th>Dept</th>
    </tr>
    </thead>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.code}</td>
            <td>${employee.name}</td>
            <td>${employee.dept}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>