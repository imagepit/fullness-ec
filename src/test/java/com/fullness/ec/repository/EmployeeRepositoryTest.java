package com.fullness.ec.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository repository;

    @Test
    void testFindAll(){
        repository.findAll().forEach(employee -> System.out.println(employee));
    }

    @Test
    void testFindById(){
        System.out.println(repository.findById(1));
    }
}
