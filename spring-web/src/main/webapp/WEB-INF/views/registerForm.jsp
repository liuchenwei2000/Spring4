<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${baseUrl}" />
    <title>Register Form</title>
</head>
<body>

<fieldset>
    <legend>Register</legend>
    <!-- form 标签中并没有设置 action 属性，这表明当表单提交时，会提交到与展现时相同的 URL 路径上。 -->
    <form method="POST">
        User Name:<input type="text" name="name"/>
        <p>
            User Age:<input type="text" name="age"/>
        <p>
            <input type="submit" value="Register"/>
    </form>
</fieldset>

<fieldset>
    <legend>Register using Validation</legend>
    <!-- 进行表单校验的注册功能。 -->
    <form action="user/register2" method="POST">
        User Name:<input type="text" name="name"/>
        <p>
            User Age:<input type="text" name="age"/>
        <p>
            <input type="submit" value="Register"/>
    </form>
</fieldset>
</body>
</html>