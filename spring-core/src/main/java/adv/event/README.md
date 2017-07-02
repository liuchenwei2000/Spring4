## Spring 事件 ##

Spring 的事件(Application Event)为 Bean 与 Bean 之间的消息通信提供了支持。
当一个 Bean 处理完一个任务之后，希望另外一个 Bean 知道并能做相应的处理，这时就需要让另外一个 Bean 监听当前 Bean 所发送的事件。

Spring 的事件需要遵循如下流程：

* 1.自定义事件，继承 ApplicationEvent
* 2.定义事件监听器，实现 ApplicationListener
