<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <base href="${baseUrl}" />
    <link rel="stylesheet" type="text/css" href="resources/style.css"/>
</head>
<body>

<div id="content">
    <!--
    根据 Spring Security 提供的默认登录页，需要关注表单的提交地址为 login，
    同时还需要 username 和 password 输入域，如果没有禁用 CSRF 的话，还需要有 _csrf 输入域。
    启用 Remember-me 功能的话，还需要 remember-me 的 checkbox。
    -->
    <form name='f' action='login' method='POST'><!-- POST 到 /login 会由 Spring Security 处理请求 -->
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username' value=''/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password'/></td>
            </tr>
            <tr>
                <td colspan='2'>
                    <input id="remember_me" name="remember-me" type="checkbox"/>
                    <label for="remember_me" class="inline">Remember me</label></td>
            </tr>
            <tr>
                <td colspan='2'>
                    <input name="submit" type="submit" value="Login"/></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
</body>
</html>
