package myapp.dao;

import myapp.Employee;

import java.util.List;

/**
 * 员工 Repository
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/3.
 */
public interface EmployeeRepository {

    Employee findByUsername(String username);

    Employee findById(String id);

    List<Employee> findAll();

    String save(Employee employee);
}
