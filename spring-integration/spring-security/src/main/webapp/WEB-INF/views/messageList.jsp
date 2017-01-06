<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${baseUrl}"/>
    <title>Message List</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Sender</th>
        <th>Content</th>
        <th>Date</th>
    </tr>
    </thead>
    <c:forEach var="message" items="${messages}">
        <tr>
            <td>${message.sender}</td>
            <td>${message.content}</td>
            <td>${message.date}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>