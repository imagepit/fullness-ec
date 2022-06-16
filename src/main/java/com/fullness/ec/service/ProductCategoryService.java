package com.fullness.ec.service;

import com.fullness.ec.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findAll();
    ProductCategory findById(Integer id);
    void insert(ProductCategory productCategory);
}
