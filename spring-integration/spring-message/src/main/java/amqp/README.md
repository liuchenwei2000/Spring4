## 使用 AMQP 实现消息功能 ##

高级消息队列协议（Advanced Message Queuing Protocol，AMQP）具有多项 JMS 所不具备的优势。

首先，AMQP 为消息定义了线路层（wire-level protocol）的协议，而 JMS 所定义的是 API 规范。JMS 的 API 协议能够确保所有的实现都能通过通用的 API 来使用，但是并不能保证某个 JMS 实现所发送的消息能够被另外不同的 JMS 实现所使用。而 AMQP 的线路层协议规范了消息的格式，消息在生产者和消费者间传送的时候会遵循这个格式。这样 AMQP 在相互协作方面就要优于 JMS——它不仅能跨不同的 AMQP 实现，还能跨语言和平台。

其次，AMQP 另外一个明显的优势在于它具有更加灵活和透明的消息模型。除了点对点模型和发布订阅模型，AMQP 还能够以其他的多种方式来发送消息，这是通过将消息的生产者和存放消息的队列解耦实现的。

Spring AMQP 是 Spring 框架的扩展，它能够在 Spring 应用中使用 AMQP 风格的消息。它提供了一个 API，借助这个 API，能够以非常类似于 Spring JMS 抽象的形式来使用 AMQP。

### AMQP 简介

在 JMS 中，通道有助于解耦消息的生产者和消费者，但是这两者依然会与通道相耦合。生产者会将消息发布到一个特定的队列或主题上，消费者从特定的队列或主题上接收这些消息。通道具有双重责任，也就是传递数据以及确定这些消息该发送到什么地方。队列的话会使用点对点算法发送，主题的话会使用发布订阅方式。

与之不同的是，AMQP 的生产者并不会直接将消息发布到队列中。AMQP 在消息的生产者以及传递消息的队列之间引入了一种间接的机制：Exchange。如下图：

![](http://imgsrc.baidu.com/forum/w%3D580/sign=1a97254653afa40f3cc6ced59b65038c/a4dc4ccf3bc79f3d91bb7644b3a1cd11738b2949.jpg)

消息的生产者将消息发布到一个 Exchange。Exchange 会绑定到一个或多个队列上，它负责将信息路由到队列上。信息的消费者会从队列中提取数据并进行处理。

在 AMQP 中，通过引入处理信息路由的 Exchange，消息的生产者与消息队列之间实现了解耦。AMQP 定义了四中类型的 Echange，每一种都有不同的路由算法，这些算法决定了是否要将信息放到队列中。根据 Exchange 的算法不同，它可能会使用消息的 routing key（可以理解成地址，指定了预期的接收者）和（或）参数，并将其与 Exchange 和队列之间 binding 的 routing key 和（或）参数进行对比。如果对比结果满足相应的算法，那么消息将会路由到队列上。否则的话，将不会路由到队列上。

四种标准的 AMQP Exchange 如下：

* Direct 如果消息的 routing key 与 binding 的 routing key 直接匹配的话，消息将会路由到该队列上。
* Topic 如果消息的 routing key 与 binding 的 routing key 符合通配符匹配的话，消息将会路由到该队列上。
* Headers 如果消息参数表中的头信息和值都与 binding 参数表中相匹配的话，消息将会路由到该队列上。
* Fanout 不管消息的 routing key 和参数表的头信息/值是什么，消息将会路由到所有队列上。

借助这四种类型的 Exchange，可以定义任意数量的路由模式，而不再仅限于点对点和发布订阅模式。当发送和接收消息的时候，所涉及的路由算法对于如何编写消息的生产者和消费者并没有什么影响。简单来讲，生产者将信息发送给 Exchange 并带有一个 routing key，消费者从队列中获取消息。

### RabbitMQ

RabbitMQ 是一种流行的开源消息代理，它实现了 AMQP。Spring AMQP 为 RabbitMQ 提供了支持，包括 RabbitMQ 连接工厂、模板以及 Spring 配置命名空间。
AMQP 章节的消息代理将使用 RabbitMQ 作为默认实现。