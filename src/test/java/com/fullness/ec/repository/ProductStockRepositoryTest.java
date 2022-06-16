package com.fullness.ec.repository;

import com.fullness.ec.entity.ProductStock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductStockRepositoryTest {
    @Autowired
    ProductStockRepository repository;
    @Test
    void testInsert(){
        ProductStock productStock = new ProductStock();
        productStock.setStock(10);
        productStock.setProductId(25);
        repository.insert(productStock);
    }
    @Test
    void testUpdate(){
        ProductStock productStock = new ProductStock();
        productStock.setId(1);
        productStock.setStock(10);
        productStock.setProductId(1);
        repository.update(productStock);
    }
}
