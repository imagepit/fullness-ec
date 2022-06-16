package com.fullness.ec.repository;

import com.fullness.ec.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository repository;
    @Test
    void testFindAll(){
        repository.findAll().forEach(category -> System.out.println(category));
    }
    @Test
    void testFindById(){
        System.out.println(repository.findById(1));
    }
    @Test
    void testInsert(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("生鮮食品");
        repository.insert(productCategory);
        repository.findAll().forEach(category -> System.out.println(category));
    }
}
