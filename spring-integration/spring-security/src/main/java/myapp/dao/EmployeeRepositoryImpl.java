package myapp.dao;

import myapp.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

    @Override
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
