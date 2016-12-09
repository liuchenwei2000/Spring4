<%-- 
异常处理页 
--%>
<%@ page contentType="text/html;charset=GBK" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<title>
			Error occured
		</title>
	</head>
	<body>
		<p>
			Error Message：<%=exception.getMessage() %>
		</p>
	</body>
</html>

