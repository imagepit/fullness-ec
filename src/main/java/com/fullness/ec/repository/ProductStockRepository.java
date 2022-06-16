package com.fullness.ec.repository;

import com.fullness.ec.entity.ProductStock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductStockRepository {
    void insert(ProductStock productStock);
    void update(ProductStock productStock);
}
