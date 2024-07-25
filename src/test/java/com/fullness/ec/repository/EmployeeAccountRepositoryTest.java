package com.fullness.ec.repository;

import com.fullness.ec.entity.Employee;
import com.fullness.ec.entity.EmployeeAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeAccountRepositoryTest {
    @Autowired
    EmployeeAccountRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Sql("/schema.sql")
    @Sql("/data.sql")
    @Test
    void insertTest(){
        EmployeeAccount account = new EmployeeAccount();
        account.setName("takahashi");
        account.setPassword(passwordEncoder.encode("takahashi"));
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("高橋");
        employee.setNameKana("タカハシ");
        employee.setDepartmentId(1);
        account.setEmployee(employee);
        repository.insert(account);
    }
}
