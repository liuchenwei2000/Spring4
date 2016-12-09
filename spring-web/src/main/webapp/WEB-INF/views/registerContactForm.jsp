<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>"/>
    <title>Register Contact Form</title>
</head>
<body>

<fieldset>
    <legend>Register Contact</legend>
    <!--
    为了支持上传文件，<form> 表现需要将 enctype 属性设为 multipart/form-data，
    这会告诉浏览器以 multipart 数据的形式提交表单，而不是以普通表单数据的形式进行提交。
    在 multipart 中，每个输入域都会对应一个 part。
    -->
    <form method="POST" enctype="multipart/form-data">
        Contact Name:<input type="text" name="name"/>
        <p>
            Contact Phone:<input type="text" name="age"/>
        <p>
            <!-- 根据 name 属性，文件数据将会发送到 multipart 请求中的 picture part 之中  -->
            Picture:<input type="file" name="picture"/><br>
        <p>
            <input type="submit" value="Register"/>
    </form>
</fieldset>
</body>
</html>