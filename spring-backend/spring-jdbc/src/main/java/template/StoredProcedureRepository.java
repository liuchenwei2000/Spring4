package template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 调用存储过程示例
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/28.
 */
@Repository
public class StoredProcedureRepository {

    // Spring 使用 SimpleJdbcCall 简化对存储过程和函数的调用
    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("read_book");// 指定存储过程名称
    }

    public Book readBook(String id) {
        // 输入参数
        SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
        Map out = simpleJdbcCall.execute(in);// 执行存储过程

        Book book = new Book();
        book.setId(id);
        book.setTitle((String) out.get("out_title"));// 输出参数
        book.setPrice(((BigDecimal) out.get("out_price")).doubleValue());// 输出参数

        return book;
    }
}
