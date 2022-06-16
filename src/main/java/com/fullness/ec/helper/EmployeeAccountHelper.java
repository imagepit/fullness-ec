package com.fullness.ec.helper;

import com.fullness.ec.entity.Employee;
import com.fullness.ec.entity.EmployeeAccount;
import com.fullness.ec.form.EmployeeAccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAccountHelper {
    @Autowired
    PasswordEncoder passwordEncoder;

    public EmployeeAccount convertEmployeeAccount(EmployeeAccountForm form){
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setName(form.getUserName());
        employeeAccount.setPassword(passwordEncoder.encode(form.getPassword()));
        Employee employee = new Employee();
        employee.setId(form.getEmpName());
        employeeAccount.setEmployee(employee);
        return employeeAccount;
    }
}
