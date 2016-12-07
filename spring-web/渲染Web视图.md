## 渲染 Web 视图 ##

将控制器中请求处理的逻辑和视图中的渲染实现解耦是 Spring MVC 的一个重要特性。如果控制器只通过逻辑视图名称来了解视图的话，那 Spring 该如何确定使用哪一个视图实现来渲染模型呢？这就是 Sring 视图解析器（View Resolver）的任务。

Spring 自带了 13 个视图解析器，能够将逻辑视图名称转换为物理实现。主要如下：

* InternalResourceViewResolver 将视图解析为 Web 应用的内部资源（一般为 JSP） 
* VelocityViewResolver 将视图解析为 Velocity 模板
* FreeMarkerViewResolver 将视图解析为 FreeMarker 模板
* TitlesViewResolver 将视图解析为 Apache Titles 定义

Spring 提供了两种支持 JSP 视图的方式：

* InternalResourceViewResolver 会将视图名解析为 JSP 文件。
* 提供了两个 JSP 标签库，一个用于表单到模型的绑定，另一个提供了通用的工具类特性。
