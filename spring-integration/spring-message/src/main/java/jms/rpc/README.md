## 使用基于消息的 RPC ##

为了支持基于消息的 RPC，Spring 提供了 JmsInvokerServiceExporter，它可以把 bean 导出为基于消息的服务；
为客户端提供了 JmsInvokerProxyFactoryBean 来使用这些服务。