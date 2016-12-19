## 使用 JMS 发送消息 ##

Java 消息服务（Java Message Service，JMS）是一个 Java 标准，定义了使用消息代理的通用 API。借助 JMS，所有遵从规范的实现都使用通用的接口，这就类似于 JDBC 为数据库操作提供了通用的接口一样。

Spring 通过基于模板的抽象为 JMS 功能提供了支持，这个模板就是 JmsTemplate。使用 JmsTemplate 能够非常容易地在消息生产方发送队列和主题消息和在消息消费方接收这些消息。

Spring 还提供了消息驱动 POJO 的理念：这是一个简单的 Java 对象，它能够以异步的方式响应队列或主题上到达的消息。
