package myapp.service;

import myapp.Employee;
import myapp.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的用户服务
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/3.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 根据给定的用户名来查找用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据真正的服务查询用户的原始信息，
        // EmployeeRepository 能够从任何数据源（关系数据库、文档数据库等）
        // 查找一个 Employee 对象，甚至可以伪造一个。
        // 而本类 UserDetailsServiceImpl 不知道也不关心底层所使用的数据存储，
        // 只要能够获得 Employee 对象，并使用它来创建 User 对象就够了。
        Employee employee = employeeRepository.findByUsername(username);
        if(employee != null) {
            // 创建权限列表
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
            // 根据不同的 Employee 为其授予不同的权限，这里可以根据应用的权限模型进行处理
            if ("admin".equalsIgnoreCase(employee.getCode())) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            // 创建 Spring Security 使用的 UserDetails 对象，这里可以根据需要实现自定义的 UserDetails
            return new User(employee.getCode(), employee.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User " + username + " not found.");
    }
}
