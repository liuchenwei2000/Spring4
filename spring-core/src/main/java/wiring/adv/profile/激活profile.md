## 激活 profile ##

Spring 在确定哪个 profile 处于激活状态时，需要依赖两个独立的属性：
spring.profiles.active 和 spring.profiles.default。

如果设置了 spring.profiles.active 属性的话，那么它的值就会用来确定哪个 profile 是激活的。
但如果没有设置 spring.profiles.active 属性的话，那将使用 spring.profiles.default 的值。
如果这两个属性都没有设置的话，那就没有激活的 profile，因此只会创建那些没有定义在 profile 中的 bean。

有多种方式来设置这两个属性：

* 作为 DispatcherServlet 的初始化参数；
* 作为 Web 应用的上下文参数；
* 作为 JNDI 条目
* 作为环境变量
* 作为 JVM 的系统属性
* 在集成测试类上，使用 @ActiveProfiles 注解设置

一种可行的方式是使用 DispatcherServlet 的参数将 spring.profiles.default 设置为开发环境的 profile，这样所有的开发人员都可以获得应用程序源码并使用开发环境的设置运行代码而无需任何额外的配置。（详见 web.xml）

当应用程序部署到测试环境或生产环境时，负责部署的人使用系统属性、环境变量或 JNDI 设置 spring.profiles.active 即可，因为系统会优先使用该属性中所设置的 profile。

另外，如果想同时激活多个 profile，可以通过列出多个 profile 名称，并以逗号分隔来实现。一般是为了同时设置多个彼此不相关的 profile。