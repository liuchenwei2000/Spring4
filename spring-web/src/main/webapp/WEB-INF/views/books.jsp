<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<c:forEach items="${books}" var="book">
    <li id="book_<c:out value="book.id"/>">
        <div class="bookTitle"><c:out value="${book.title}"/></div>
        <div>
            <span class="bookAuthor"><c:out value="${book.author}"/></span>
        </div>
    </li>
</c:forEach>
</body>
</html>