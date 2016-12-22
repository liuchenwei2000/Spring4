## 借助 Spring Boot 简化 Spring 开发 ##

Spring Boot 提供了四个主要的特性，能够改变开发 Spring 应用程序的方式：

* Spring Boot Starter
  它将常用的依赖分组进行了整合，将其合并到一个依赖中，这样就可以一次性添加到项目的 Maven 或 Gradle 构建中。
* 自动配置
  利用 Spring4 对条件化配置的支持，合理地推测应用所需的 bean 并自动化配置它们。
* 命令行接口（Command-line interface，CLI）
  CLI 发挥了 Groovy 语言的优势，并结合自动配置进一步简化 Spring 应用的开发。
* Actuator
  为 Spring Boot 应用添加了一定的管理特性。

### Starter

Spring Boot Starter 将应用所需的常见依赖按组聚集在一起，形成单条依赖，并且由 Starter 所提供的依赖版本能够相互兼容。它提供了多个 Starter，常用的如下：

		  Starter                      								所提供的依赖
	spring-boot-starter							spring-boot、spring-boot-autoconfigure、spring-boot-starter-logging
	spring-boot-starter-actuator				spring-boot-starter、spring-boot-actuator、spring-core
	spring-boot-starter-amqp					spring-boot-starter、spring-boot-rabbit、spring-core、spring-tx
	spring-boot-starter-aop						spring-boot-starter、spring-core、spring-aop
	spring-boot-starter-elasticsearch			spring-boot-starter、spring-data-elasticsearch、spring-core、spring-tx
	spring-boot-starter-data-jpa				spring-boot-starter、spring-boot-starter-jdbc、spring-core、spring-orm、spring-data-jpa
	spring-boot-starter-data-rest				spring-boot-starter、spring-boot-starter-web、spring-core、spring-tx、spring-data-rest-webmvc
	spring-boot-starter-data-solr				spring-boot-starter、SolrJ、spring-core、spring-tx、spring-data-solr
	spring-boot-starter-jdbc					spring-boot-starter、spring-jdbc、spring-tx
	spring-boot-starter-redis					spring-boot-starter、spring-data-redis
	spring-boot-starter-security				spring-boot-starter、spring-web、spring-security-config、spring-security-web
	spring-boot-starter-web						spring-boot-starter、jackson-databind、spring-web、spring-webmvc
	spring-boot-starter-ws						spring-boot-starter、spring-boot-starter-web、spring-jms、spring-ws-core、spring-ws-support

这些 Starter 依赖的原理使用了 Maven 和 Gradle 的依赖传递方案，Starter 在自己的 pom.xml 文件中声明了多个依赖。当将某一个 Starter 依赖添加到 Maven 或 Gradle 构建中的时候，Starter 的依赖会自动地传递性解析。这些依赖本身可能也会有其他的依赖，一个 Starter 可能会传递性地引入几十个依赖。很多 Starter 引用了其他的 Starter，大多数 Starter 都会引用 spring-boot-starter，它实际上是一个基础的 Starter。

### 自动配置

Spring Boot 的 Starter 减少了构建中依赖列表的长度，而自动配置功能则减少了 Spring 配置的数量。它在实现时，会考虑应用中的其他因素并推断你所需要的 Spring 配置。

比如将 FreeMarker 添加到项目的类路径中，Spring Boot 探测到之后，就会推断需要使用 FreeMarker 实现 SpringMVC 的视图功能，并自动配置这些 bean。

又比如将 Web Starter 作为依赖放入构建中以后，Spring Boot 探测到 SpringMVC 位于类路径下，它将会自动配置支持 SpringMVC 的多个 bean，包括视图解析器、资源处理器以及消息转换器等等。

### CLI
	
Spring Boot CLI 充分利用了 Spring Boot Starter 和自动配置的魔力，并添加了一些 Groovy 的功能。它简化了 Spring 的开发流程，通过 CLI 能够运行一个或多个 Groovy 脚本，并查看它是如何运行的。在应用的运行过程中，CLI 能够自动导入 Spring 类型并解析依赖。

### Actuator

Spring Boot Actuator 为 Spring Boot 项目带来了很多有用的特性，如下：

* 管理端点
* 合理的异常处理以及默认的 "/error" 映射端点
* 获取应用信息的 "/info" 端点
* 当启用 Spring Security 时，会有一个审计事件框架

