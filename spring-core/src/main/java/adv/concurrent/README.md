## Spring 多线程 ##

Spring 通过任务执行器（TaskExecutor）来实现多线程和并发编程。
使用 ThreadPoolTaskExecutor 可实现一个基于线程池的 TaskExecutor。
而实际开发中任务一般是非阻塞的，即异步的，所以要在配置类中通过 @EnableAsync 开启对异步任务的支持，并通过在实际执行的 Bean 的方法中使用 @Async 注解来声明其是一个异步任务。
