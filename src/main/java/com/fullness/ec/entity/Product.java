package com.fullness.ec.entity;

import lombok.Data;

/**
 * 商品テーブルエンティティ
 */
@Data
public class Product {
    /** 商品ID */
    private Integer id;
    /** 商品名 */
    private String name;
    /** 商品単価 */
    private Integer price;
    /** 画像URL */
    private String imageUrl;
    /** 商品カテゴリ */
    private ProductCategory productCategory;
    /** 商品在庫 */
    private ProductStock stock;
    /** 削除フラグ */
    private Integer deleteFlag;
}
