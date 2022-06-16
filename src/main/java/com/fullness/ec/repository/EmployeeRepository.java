package com.fullness.ec.repository;

import com.fullness.ec.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeRepository {
    List<Employee> findAll();
    Employee findById(Integer id);
}
