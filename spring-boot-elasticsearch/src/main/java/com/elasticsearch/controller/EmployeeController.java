package com.elasticsearch.controller;
import com.elasticsearch.dao.EmployeeRepository;
import com.elasticsearch.entity.Employee;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("es")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 添加
     * @return
     */
    @RequestMapping("add")
    public String add() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("Queen");
        employee.setLastName("Oli");
        employee.setAge(28);
        employee.setAbout("i am Green Arrow");
        employeeRepository.save(employee);
        return "success";
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping("delete")
    public String delete() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        employeeRepository.delete(employee);
        return "success";
    }

    /**
     * 局部更新
     * @return
     */
    @RequestMapping("update")
    public String update() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        employee.setFirstName("Flash");
        employeeRepository.save(employee);
        return "success";
    }
    /**
     * 查询
     * @return
     */
    @RequestMapping("query")
    public Employee query() {
        Employee accountInfo = employeeRepository.queryEmployeeById("1");
        System.err.println(new Gson().toJson(accountInfo));
        return accountInfo;
    }
}

