<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<c:forEach items="${users}" var="user">
    <li id="user_<c:out value="user.id"/>">
        <div class="userName"><c:out value="${user.name}"/></div>
        <div>
            <span class="userAge"><c:out value="${user.age}"/></span>
        </div>
    </li>
</c:forEach>
</body>
</html>