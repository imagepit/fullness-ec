package com.fullness.ec.repository;

import com.fullness.ec.entity.EmployeeAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeAccountRepository {
    EmployeeAccount findByUsername(String userName);
    void insert(EmployeeAccount employeeAccount);
}
