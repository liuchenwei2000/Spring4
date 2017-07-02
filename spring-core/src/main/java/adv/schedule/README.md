## Spring 计划任务 ##

从 Spring3.1 开始，计划任务在 Spring 中的实现变得异常的简单。
首先通过在配置类注解 @EnableScheduling 来开启计划任务的支持，然后在要执行计划任务的方法上注解 @Scheduled 来声明这是一个计划任务。

Spring 通过 @Scheduled 支持多种类型的计划任务，包含 cron、fixDelay、fixRate 等。