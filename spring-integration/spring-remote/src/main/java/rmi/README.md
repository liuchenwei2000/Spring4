## 使用 RMI ##

Spring 简化了 RMI 模型，它提供了一个代理工厂 bean，能让我们把 RMI 服务像本地 JavaBean 那样装配到 Spring 应用中。Spring 还提供了一个远程导出器，用来简化把 Spring 管理的 bean 转换为 RMI 服务的工作。

![](http://imgsrc.baidu.com/forum/w%3D580/sign=7be02c573f7adab43dd01b4bbbd5b36b/db1c6e66d016092428e13d64dd0735fae4cd34ee.jpg)

RMI 是一种实现远程服务交互的好办法，但是它存在某些限制。首先，RMI 很难穿越防火墙，这是因为 RMI 使用任意端口来交互——这是防火墙通常所不允许的。在企业内部网络环境中，通常不需要担心这个问题。但是如果在互联网上运行，使用 RMI 可能会遇到麻烦。即使 RMI 提供了对 HTTP 的通道的支持（通常防火墙都允许），但是建立这个通道也不是件容易的事。

另外，RMI 是基于 Java 的，这意味着客户端和服务端必须都是用 Java 开发的。因为 RMI 使用了 Java 的序列化机制，所以通过网络传输的对象类型必须要保证在调用两端的 Java 运行时中是完全相同的版本。