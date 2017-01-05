<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 声明 Spring Security 的 JSP 标签库 -->
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Your Detail Info</title>
    <base href="${baseUrl}"/>
</head>
<body>
    <%--
    <security:authentication> 标签可以用来渲染当前用户认证对象的详细信息。支持的属性如下：

    authorities：一组用于表示用户所授予权限的 GrantedAuthority 对象
    credentials：用于核实用户的凭证（通常这会是密码）
    details：认证的附加信息（IP 地址、证件序列号、会话 ID 等）
    principal：用户的基本信息对象
    --%>
<p>
    Hello <security:authentication property="principal.username" /> !
    <!--
    如果想将渲染属性的值赋给一个变量，可以使用 var 属性指明变量的名字。
    这个变量默认是定义在页面作用域内的，但也可以通过 scope 属性将其设为请求或会话作用域。
    -->
    <%--
    <security:authentication property="principal.username" var="loginUser" scope="session" />
    --%>
</p>
    <p>
        Id:${employee.id}
    <p>
        Code:${employee.code}
    <p>
        Name:${employee.name}
    <p>
        Dept:${employee.dept}
    <p>
        <%--
        <security:authorize> 标签能够根据用户被授予的权限有条件地渲染页面的部分内容。
        比如下面所示只允许具有 ROLE_ADMIN 角色的用户才能看到 Salary 信息。
        access 属性的值是一个 SpEL 表达式，这个表达式的值将确定标签体内的内容是否被渲染。
        --%>
        <security:authorize access="hasRole('ROLE_ADMIN')">
        Salary:${employee.salary}
    <p>
        </security:authorize>

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