## 使用 Spring Security 保护应用 ##

Spring Security 是一种基于 Spring AOP 和 Servlet 规范中的 Filter 实现的安全框架。它为基于 Spring 的应用程序提供声明式安全保护，能够在 Web 请求级别和方法调用级别处理身份认证和授权。因为基于 Spring 框架，所以 Spring Security 充分利用了依赖注入和面向切面的技术。

Spring Security 从两个角度来解决安全性问题，它使用 Servlet 规范中的 Filter 保护 Web 请求并限制 URL 级别的访问。Spring Security 还能够使用 Spring AOP 保护方法调用——借助于对象代理和使用通知，能够确保只有具备适当权限的用户才能访问安全保护的方法。

Spring Security 被分为 11 个模块，如下：

				模块											描述
	ACL(spring-security-acl)				支持通过访问控制列表（Access Control List）为域对象提供安全性
	切面(spring-security-aspects)			当使用注解时，会使用基于 AspectJ 的切面，而不是标准的 Spring AOP
	CAS 客户端(spring-security-cas)			提供与 Jasig 的中心认证服务（Central Authentication Service）进行集成的功能
	配置(spring-security-config)				包含通过 XML 和 Java 配置 Spring Security 的功能支持
	核心(spring-security-core)				提供 Spring Security 基本库
	加密(spring-security-crypto)				提供加密和密码编码的功能
	LDAP(spring-security-ldap)				支持基于 LDAP 进行认证
	OpenID(spring-security-openid)			支持使用 OpenID 进行集中式认证
	Remoting(spring-security-remoting)		提供了对 Spring Remoting 的支持
	标签库(spring-security-taglibs)			Spring Security 的 JSP 标签库
	Web(spring-security-web)				提供了基于 Filter 的 Web 安全性支持。

对于一般的 Web 应用程序，至少要包含 Core、Config 和 Web 这三个模块。详见 pom.xml。

### 防止跨站请求伪造

跨站请求伪造（cross-site request forgery，CSRF），简单来讲，如果一个站点欺骗用户提交请求到其他服务器的话，就会发生 CSRF 攻击，这可能会带来消极的后果。

从 Spring Security 3.2 开始，默认就会启用 CSRF 保护。实际上，除非采取行为处理 CSRF 防护或者将这个功能禁用，否则的话，在应用提交表单时可能会遇到问题。

Spring Security 通过一个同步 token 的方式来实现 CSRF 防护的功能。它将会拦截状态变化的请求（非 GET、HEAD、OPTIONS 和 TRACE 的请求）并检查 CSRF token。如果请求中不包含 CSRF token 的话，或者 token 不能与服务器端的 token 相匹配，请求将会失败，并抛出 CsrfException 异常。

这意味着在应用中，所有的表单必须在一个 "_csrf" 域中提交 token，而且这个 token 必须要与服务器端计算并存储的 token 一致，这样的话当表单提交的时候，才能进行匹配。详见 WEB-INF/views/form.jsp。

### 保护视图

当为浏览器渲染 HTML 内容时，可能希望视图中能够反映安全限制和相关的信息。一个简单的样例就是渲染用户的基本信息（比如显示"您已经以 *** 身份登录"），或者想根据用户被授予了什么权限，有条件地渲染特定的视图元素。

Spring Security 提供了一个 JSP 标签库，在视图层上支持安全性。详见 WEB-INF/views/employee.jsp。

### 保护方法调用

Spring Security 保护应用的 Web 层时能阻止用户访问没有权限的内容，但是，如果 Web 层出现安全漏洞，用户就可以请求他们不允许访问的内容了。使用 Spring Security 还能够保护 Web 层后面的 bean 方法，通过这种方式，就能声明安全规则，保证如果用户没有执行方法的权限，就不会调用相应的方法。

实现方法级安全性最常见的办法是使用特定的注解，将这些注解应用到需要保护的方法上。Spring Security 提供了三种不同的安全注解：

* Spring Security 自带的 @Secured 注解
* JSR-250 的 @RolesAllowed 注解
* 表达式驱动的注解，包括 @PreAuthorize、@PostAuthorize、@PreFilter 和 @PostFilter。

具体使用详见 myapp.dao.EmployeeRepositoryImpl 类。