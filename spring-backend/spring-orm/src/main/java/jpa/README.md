## Spring 和 JPA ##

在 Spring 中使用 JPA 的第一步是要在 Spring 应用上下文中将实体管理器工厂（entity manager factory）按照 bean 的形式来进行配置。

基于 JPA 的应用程序需要使用 EntityManagerFactory 的实现类来获取 EntityManager 实例。JPA 定义了两种类型的实体管理器：

* 应用程序管理类型（Application-managed）
  
  当应用程序向实体管理器工厂直接请求实体管理器时，工厂会创建一个实体管理器。在这种模式下，程序要自己负责打开或关闭实体管理器并在事务中对其进行控制。这种方式适合于不运行在 JavaEE 容器中的独立应用程序。

* 容器管理类型（Container-managed）

  实体管理器由 JavaEE 容器创建和管理，应用程序根本不与实体管理器工厂打交道。相反，实体管理器直接通过注入或 JNDI 来获取。容器负责配置实体管理器工厂。这种类型的实体管理器最适合用于 JavaEE 容器，在这种情况下会希望在 persistence.xml 指定的 JPA 配置之外保持一些自己对 JPA 的控制。

这两种实体管理器实现了同一个 EntityManager 接口，关键的区别不在于 EntityManager 本身，而在于 EntityManager 的创建和管理方式。不管使用哪种 EntityManagerFactory，Spring 都会负责管理 EntityManager。如果使用的是应用程序管理类型，Spring 承担了应用程序的角色并以透明的方式处理 EntityManager。在容器管理的场景下，Spring 会担当容器的角色。

以上两种实体管理器工厂分别由对应的 Spring 工厂 Bean 创建：

* LocalEntityManagerFactoryBean 生成应用程序管理类型的 EntityManagerFactory
* LocalContainerEntityManagerFactoryBean 生成容器管理类型的 EntityManagerFactory

选择哪一种 EntityManagerFactory 对于基于 Spring 的应用程序来讲是完全透明的。当组合使用 Spring 和 JPA 时，处理 EntityManagerFactory 的复杂细节被隐藏了起来，应用代码只需关注数据访问即可。