package com.fullness.ec.repository;

import com.fullness.ec.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCategoryRepository {
    List<ProductCategory> findAll();
    ProductCategory findById(Integer id);
    void insert(ProductCategory productCategory);
}
