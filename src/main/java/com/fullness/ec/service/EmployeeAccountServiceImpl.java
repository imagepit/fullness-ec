package com.fullness.ec.service;

import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.repository.EmployeeAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeAccountServiceImpl implements EmployeeAccountService{
    @Autowired
    EmployeeAccountRepository repository;

    @Override
    public void insert(EmployeeAccount employeeAccount) {
        repository.insert(employeeAccount);
    }
}
