## 创建消息驱动的 POJO ##

使用 JmsTemplate 接收消息的最大缺点在于 receive() 和 receiveAndConvert() 方法都是同步的。
这意味着接收者必须耐心等待消息的到来，因此这些方法会一直被阻塞，直到有可用消息（或者超时）。
Spring 提供了它自己的消息驱动 bean 来满足异步接收消息的需求，它与 EJB3 的 MDB 很相似，但仍是 POJO。