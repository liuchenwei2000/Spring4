## 使用基于 HTTP 协议的技术发布远程服务 ##

### 使用 Hessian 和 Burlap 发布远程服务

Hessian 和 Burlap 是 Caucho Technology 提供的两种基于 HTTP 的轻量级远程服务解决方案。借助于尽可能简单的 API 和通信协议，它们都致力于简化 Web 服务。

Hessian 和 RMI 一样，使用二进制消息进行客户端和服务端的交互，但与 RMI 不同的是，它的二进制消息可以移植到其他非 Java 语言中，包括 PHP、Python、C++ 和 C#。

Burlap 是一种基于 XML 的远程调用技术，这使得它可以自然而然地移植到任何能够解析 XML 的语言上。正因为它基于 XML，所以相比起 Hessian 的二进制格式而言，Burlap 可读性更强。但是和其他基于 XML 的远程技术（如 SOAP 或 XML-RPC）不同，Burlap 的消息结构尽可能的简单，不需要额外的外部定义语言（例如 WSDL 或 IDL）。

由于 Hessian 的消息是二进制的，所以它在带宽上更具优势。但是如果更注重可读性或者需要与没有 Hessian 实现的语言交互，选择 Burlap 会更好。

因为 Hessian 和 Burlap 都是基于 HTTP 的，它们都解决了 RMI 的防火墙渗透问题。但是当传递过来的 RPC 消息中包含序列化对象时，RMI 就完胜 Hessian 和 Burlap了。因为 Hessian 和 Burlap 都采用了私有的序列化机制，而 RMI 使用的是 Java 本身的序列化机制。

### 使用 Spring 的 HttpInvoker 发布远程服务

Spring 的 HttpInvoker 是一个新的远程调用模型，作为 Spring 框架的一部分，能够执行基于 HTTP 的远程调用（让防火墙不为难），并使用 Java 的序列化机制（让开发者也乐观其变）。使用基于 HttpInvoker 的服务和使用基于 Hessian/Burlap 的服务非常相似。

HttpInvoker 只是一个 Spring 框架所提供的远程调用解决方案，这意味着客户端和服务端必须都是 Spring 应用。另外，因为使用了 Java 的序列化机制，客户端和服务端必须使用相同版本的类。

本章选用 Hessian 和 HttpInvoker 作为示例，Burlap 的配置跟这两个示例几乎一模一样，除了 Exporter 不同。
