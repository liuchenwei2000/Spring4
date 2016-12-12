## Spring JDBC 模板 ##

Spring 的 JDBC 模板承担了资源管理和异常处理的工作，从而简化了 JDBC 代码，使得只需编写从数据库读写数据的必需代码。

Spring 将数据访问的样板代码抽象到模板类之中，它提供了两个模板类供选择：

* JdbcTemplate 最基本的 JDBC 模板，支持简单的 JDBC 数据库访问功能和基于索引参数的查询 
* NamedParameterJdbcTemplate 该模板类执行查询时可以将值以命名参数的形式绑定到 SQL 中，而不是使用简单的索引参数。
