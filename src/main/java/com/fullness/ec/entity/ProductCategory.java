package com.fullness.ec.entity;

import lombok.Data;

/**
 * 商品カテゴリテーブルエンティティ
 */
@Data
public class ProductCategory {
    /** 商品カテゴリID */
    private Integer id;
    /** 商品カテゴリ名 */
    private String name;
}
