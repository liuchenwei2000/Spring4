## 处理异常 ##

Servlet 请求的输出始终会是一个响应，如果在请求处理的时候出现了异常，那么它的输出依然会是 Servlet 响应。异常必须要以某种方式转换为响应。

Spring 提供了多种方式将异常转换为响应：

* 特定的 Spring 异常将自动映射为指定的 HTTP 状态码；
* 异常上可以添加 @ResponseStatus 注解，从而将其映射为某一个 HTTP 状态码；
* 在方法上可以添加 @ExceptionHandler 注解，使其用来处理异常

### 将异常映射为 HTTP 状态码

在默认情况下，Spring 会将自身的一些异常自动转换为合适的状态码。比如将 BindException 转换为 400，ConversionNotSupportedException 转换为 500，NoSuchRequestHandlingMethodException 转换为 404。

这些异常在 DispatcherServlet 处理过程中或执行校验时产生，会自动被处理并产生相应状态吗的响应。但是对于应用所抛出的其他异常就无能为力了，只能通过添加 @ResponseStatus 注解将异常映射为 HTTP 状态码。

