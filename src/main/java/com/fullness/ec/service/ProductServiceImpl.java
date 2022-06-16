package com.fullness.ec.service;

import com.fullness.ec.entity.Product;
import com.fullness.ec.repository.ProductRepository;
import com.fullness.ec.repository.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductStockRepository productStockRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findPage(Pageable pageable) {
        Integer total = productRepository.countAll();
        List<Product> products;
        if(total > 0){
            products = productRepository.findPage(pageable);
        } else {
            products = Collections.emptyList();
        }
        return new PageImpl<>(products,pageable,total);
    }

    @Override
    public List<Product> findByCategoryId(int productCategoryId) {
        return productRepository.findByCategoryId(productCategoryId);
    }

    @Override
    public Page<Product> findPageByCategoryId(int productCategoryId, Pageable pageable) {
        Integer total = productRepository.countAllByCategoryId(productCategoryId);
        List<Product> products;
        if(total > 0){
            products = productRepository.findPageByCategoryId(productCategoryId,pageable);
        } else {
            products = Collections.emptyList();
        }
        return new PageImpl<>(products,pageable,total);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public void insert(Product product) {
        productRepository.insert(product);
        product.getStock().setProductId(product.getId()); // 生成された商品IDを商品在庫にセット
        productStockRepository.insert(product.getStock());
    }

    @Transactional
    @Override
    public void update(Product product) {
        productRepository.update(product);
        productStockRepository.update(product.getStock());
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }
}
