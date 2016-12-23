package myapp.dao;

import myapp.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Book DAO 实现类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Component
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private JdbcOperations jdbcTemplate;

    public List<Book> getBooks(int count) {
        String sql = "select id, title, author from tb_book limit " + count;
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    private static final class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book();
            book.setId(resultSet.getString("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            return book;
        }
    }
}
