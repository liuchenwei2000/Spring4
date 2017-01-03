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