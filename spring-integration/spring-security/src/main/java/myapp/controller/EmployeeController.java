package myapp.controller;

import myapp.Employee;
import myapp.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * EmployeeController
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/3.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String user(Model model, @PathVariable("id") String id) {
        Employee employee = employeeRepository.findById(id);
        model.addAttribute("employee", employee);
        return "employee";
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public String getInfo(Model model) {
        // 获取 Spring Security 在登录时添加进 session 的用户 UserDetails
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Employee employee = employeeRepository.findByUsername(user.getUsername());

        model.addAttribute("employee", employee);
        return "employee";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String form() {
        return "form";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String save(Employee employee) {
        String id = employeeRepository.save(employee);
        return "redirect:/employee/" + id;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(Model model) {
        List<Employee> employees = employeeRepository.findAll();

        model.addAttribute("employees", employees);
        return "employeeList";
    }
}
