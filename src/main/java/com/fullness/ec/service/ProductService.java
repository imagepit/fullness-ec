package com.fullness.ec.service;

import com.fullness.ec.entity.Product;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Page<Product> findPage(Pageable pageable);
    List<Product> findByCategoryId(int productCategoryId);
    Page<Product> findPageByCategoryId(int productCategoryId,Pageable pageable);
    Product findById(Integer id);
    void insert(Product product);
    void update(Product product);
    void delete(Integer id);
}
