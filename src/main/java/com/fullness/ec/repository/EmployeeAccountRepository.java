package com.fullness.ec.repository;

import com.fullness.ec.entity.EmployeeAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeAccountRepository {
    EmployeeAccount findByUsername(String userName);
    void insert(EmployeeAccount employeeAccount);
}
