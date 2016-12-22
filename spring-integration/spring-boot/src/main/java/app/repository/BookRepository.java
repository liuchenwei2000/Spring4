package app.repository;

import app.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/22.
 */
@Repository
public class BookRepository {

    // Spring Boot 探测到 Spring JDBC 模块和 MySQL 驱动包在类路径下的时候，
    // 会自动配置 JdbcTemplate bean 和 MySQL DataSource bean。其中 MySQL 数据库的各项属性
    // 都配置在根路径下的 application.properties 文件中（即resources/application.properties）。
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        String sql = "select id,name,author from boot_book order by name";
        return jdbcTemplate.query(sql, new RowMapper<Book>() {

            @Override
            public Book mapRow(ResultSet rs, int i) throws SQLException {
                Book book = new Book();
                book.setId(rs.getString(1));
                book.setName(rs.getString(2));
                book.setAuthor(rs.getString(3));
                return book;
            }
        });
    }
}
