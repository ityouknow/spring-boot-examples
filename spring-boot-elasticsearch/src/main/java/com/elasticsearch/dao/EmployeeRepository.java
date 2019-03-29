package com.elasticsearch.dao;

import com.elasticsearch.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String>{

    /**
     * 查询雇员信息
     * @param id
     * @return
     */
    Employee queryEmployeeById(String id);
}

