package com.fullness.ec.repository;

import com.fullness.ec.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Mapper
public interface ProductRepository {
    List<Product> findAll();
    List<Product> findPage(@Param("pageable")Pageable pageable);
    Integer countAll();
    List<Product> findByCategoryId(int productCategoryId);
    List<Product> findPageByCategoryId(int productCategoryId,@Param("pageable")Pageable pageable);
    Integer countAllByCategoryId(int productCategoryId);
    Product findById(Integer id);
    void insert(Product product);
    void update(Product product);
    void delete(Integer id);
}