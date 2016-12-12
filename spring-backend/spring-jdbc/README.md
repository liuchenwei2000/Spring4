## Spring 和 JDBC ##

Spring 应用需要从某种类型的数据库中读取和写入数据。为了避免持久化的逻辑分散到应用的各个组件中，最好将数据访问的功能放到一个或多个专注于此项任务的组件中。这样的组件通常被称为数据访问对象（DAO）或 Repository。为了避免应用与特定的数据访问策略耦合在一起，编写良好的 Repository 应该以接口的方式暴露功能。

### Spring 的数据访问异常体系

一方面，JDBC 的异常体系过于简单；另一方面，ORM 框架的异常体系是其本身所独有的。Spring JDBC 提供的数据访问异常体系解决了以上两个问题。

不同于 JDBC，Spring 提供了多个数据访问异常，分别描述了它们抛出时所对应的问题，它为读取和写入数据库的几乎所有错误都提供了异常。尽管 Spring 的异常体系比 JDBC 简单的 SQLException 丰富得多，但它并没有与特定的持久化方式关联。这意味着可以使用 Spring 抛出一致的异常，而不用关系所选择的持久化方案，这有助于将持久化机制与数据访问层隔离开来。

Spring 的数据访问异常都继承自 DataAccessException——这是一个 RuntimeException，也就是说，没有必要捕获 Spring 抛出来的数据访问异常。当然，如果想要捕获的话也完全可以。

### 数据访问模板化

Spring 将数据访问过程中固定的和可变的部分明确划分为两个不同的类：模板（template）和回调（callback）。模板管理过程中固定的部分（如事务控制、管理资源以及处理异常），而回调处理自定义的数据访问代码（如 SQL 语句、绑定参数以及整理结果集）。

针对不同的持久化平台，Spring 提供了多个可选的模板。如下：

		模板类                         用途
    JdbcTemplate                   JDBC 连接 
	NamedParameterJdbcTemplate	   支持命名参数的 JDBC 连接
	HibernateTemplate	           Hibernate 3.x 以上的 Session
	SqlMapClientTemplate           MyBatis SqlMap 客户端
	JpqTemplate                    JPA 的实体管理器