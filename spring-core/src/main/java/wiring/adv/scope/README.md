## bean 的作用域 ##

Spring 应用上下文中所有 bean 都是作为单例（singleton）的形式创建的，也就是说，不管给定的一个 bean 被注入到其他 bean 多少次，每次所注入的都是同一个实例。

Spring 定义了多种作用域，可以基于这些作用域创建 bean，包括：

* 单例（singleton）：在整个应用中，只创建 bean 的一个实例。
* 原型（prototype）：每次注入或者通过 Spring 应用上下文获取的时候，都会创建一个新的 bean 实例。
* 会话（session）：在 Web 应用中，为每个会话创建一个 bean 实例。
* 请求（request）：在 Web 应用中，为每个请求创建一个 bean 实例。 