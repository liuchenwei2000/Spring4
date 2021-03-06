## 通过 Java 代码装配 Bean ##

某些情况下，比如要将第三方库中的组件组装到你的应用中，是没有办法在它的类上添加 @Component 和 @Autowired 注解的。
因此就不能使用自动化装配的方案，必须使用显式配置，而 Java 配置是更好的方案，因为更强大、类型安全并且对重构友好。

Java 配置代码跟其他的 Java 代码有所区别，它仅仅是配置代码，这意味着它不应该包含任何业务逻辑，也不应该侵入到业务逻辑代码中。
尽管不是必须的，但通常会将 Java 配置代码放到单独的包中，使其与业务代码分离开来。