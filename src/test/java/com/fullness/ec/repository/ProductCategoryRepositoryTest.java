package com.fullness.ec.repository;

import com.fullness.ec.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository repository;
    @Sql("/schema.sql")
    @Sql("/data.sql")
    @Test
    void testFindAll(){
        repository.findAll().forEach(category -> System.out.println(category));
    }
    @Sql("/schema.sql")
    @Sql("/data.sql")
    @Test
    void testFindById(){
        System.out.println(repository.findById(1));
    }
    @Sql("/schema.sql")
    @Sql("/data.sql")
    @Test
    void testInsert(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("生鮮食品");
        repository.insert(productCategory);
        repository.findAll().forEach(category -> System.out.println(category));
    }
}
