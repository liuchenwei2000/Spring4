<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Successful!</title>
    <base href="${baseUrl}"/>
</head>
<body>
<p>
    <security:authentication property="principal.username" />，your operation is OK.
<p>
    [Message]：${message.content}
<p>
</body>
</html>