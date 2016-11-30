## 自动化装配 Bean ##

Spring 从两个角度来实现自动化装配：

* 组件扫描（component scanning）：自动发现应用上下文中所创建的 bean。
* 自动装配（autowiring）：自动满足 bean 之间的依赖。

组件扫描和自动装配组合在一起就能发挥出强大的威力，将显示配置降低到最少。