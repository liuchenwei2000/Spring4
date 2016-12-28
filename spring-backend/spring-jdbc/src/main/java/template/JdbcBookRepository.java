package template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Book Repository 实现
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/12.
 */
@Repository
public class JdbcBookRepository implements BookRepository {

    // 自动注入 JdbcTemplate 和 NamedParameterJdbcTemplate
    @Autowired
    private JdbcOperations jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    @Override
    public void save(Book book) {
        // 样板代码被巧妙地隐藏到了 JDBC 模板类中，在内部，模板类将会捕获所有可能抛出的 SQLException
        // 并将通用的 SQLException 转换为 Spring 自定义的更明确的数据访问异常后重新抛出。

        // 使用命名参数查询，绑定值的顺序就不重要了，可以按照名字来帮定值，而无须担心参数顺序发生改变。
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("id", book.getId());
        parameters.put("title", book.getTitle());
        parameters.put("price", book.getPrice());

        npJdbcTemplate.update("insert into s_book(id, title, price) values(:id,:title,:price)", parameters);
    }

    @Override
    public boolean delete(String id) {
        int result = jdbcTemplate.update("delete from s_book where id=?", id);
        return result == 1;
    }

    @Override
    public Book find(String id) {
        // 对于查询返回的每一行数据，JdbcTemplate 将会调用 RowMapper 的
        // mapRow() 方法，并传入一个 ResultSet 和包含行号的整数，
        // 在 BookRowMapper 的 mapRow() 方法中会创建 Book 对象并将 ResultSet 中的值填充进去。
        return jdbcTemplate.queryForObject("select id, title, price from s_book where id=?",
                new BookRowMapper(), id);
    }

    /**
     * RowMapper 用于从 ResultSet 中提取数据并构建成域对象。
     */
    private static final class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book();
            book.setId(resultSet.getString("id"));
            book.setTitle(resultSet.getString("title"));
            book.setPrice(resultSet.getDouble("price"));
            return book;
        }
    }
}


