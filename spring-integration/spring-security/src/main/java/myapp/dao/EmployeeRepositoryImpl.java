package myapp.dao;

import myapp.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工 Repository 实现
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/3.
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    public Employee findByUsername(String username) {
        String sql = "select id, code, name, password, dept, salary from myapp_employee where code=:code";

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("code", username);

        return npJdbcTemplate.queryForObject(sql, parameters, new EmployeeRowMapper());
    }

    public Employee findById(String id) {
        String sql = "select id, code, name, password, dept, salary from myapp_employee where id=:id";

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);

        return npJdbcTemplate.queryForObject(sql, parameters, new EmployeeRowMapper());
    }

    public String save(Employee employee) {
        String sql = "insert into myapp_employee( id, code, name, password, dept, salary) " +
                " values(:id,:code,:name,:password,:dept,:salary)";

        String id = "A0" + ((int) (Math.random() * 1000));

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("code", employee.getCode());
        parameters.put("name", employee.getName());
        parameters.put("password", employee.getPassword());
        parameters.put("dept", employee.getDept());
        parameters.put("salary", employee.getSalary());

        npJdbcTemplate.update(sql, parameters);

        return id;
    }

    /**
     * 使用注解保护方法
     * <p>
     *     &nbsp;@Secured 注解会使用一个 String 数组作为参数，
     *     每个 String 值是一个权限，调用这个方法至少需要具备其中的一个权限。
     * <p>
     *     如果方法被没有认证的用户或者所需权限的用户调用，保护这个方法的切面将抛出异常。
     *     可能是 Spring Security 的 AuthenticationException 或 AccessDeniedException 的子类。
     *     它们是非受查异常，但这个异常最终必须要被捕获和处理。
     *     如果被保护的方法是在 Web 请求中调用的，这个异常会被 Spring Security 的过滤器自动处理。
     *     否则的话，就需要编码来处理这个异常。
     */
    @Secured("ROLE_ADMIN")
//    @RolesAllowed("ROLE_ADMIN")
    public List<Employee> findAll() {
        String sql = "select id, code, name, password, dept, salary from myapp_employee";

        return npJdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    private static final class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setId(resultSet.getString("id"));
            employee.setCode(resultSet.getString("code"));
            employee.setName(resultSet.getString("name"));
            employee.setPassword(resultSet.getString("password"));
            employee.setDept(resultSet.getString("dept"));
            employee.setSalary(resultSet.getDouble("salary"));
            return employee;
        }
    }
}
