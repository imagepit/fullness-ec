package com.fullness.ec.service;

import com.fullness.ec.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Integer id);
}
