package myapp.dao;

import myapp.Employee;

/**
 * 员工 Repository
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/3.
 */
public interface EmployeeRepository {

    Employee findByUsername(String username);

    Employee findById(String id);

    String save(Employee employee);
}
