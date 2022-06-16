package com.fullness.ec.repository;

import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository repository;
    @Test
    void testFindAll(){
        repository.findAll().forEach(product -> System.out.println(product));
    }
    @Test
    void testInsert(){
        Product product = new Product();
        product.setName("テスト商品1");
        product.setPrice(1000);
        product.setImageUrl("test.png");
        product.setDeleteFlag(0);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1);
        product.setProductCategory(productCategory);
        repository.insert(product);
        repository.findAll().forEach(p -> System.out.println(p));
    }
    @Test
    void testDelete(){
        repository.delete(1);
    }
}
