package com.fullness.ec.entity;

import lombok.Data;

/**
 * 商品在庫テーブルエンティティ
 */
@Data
public class ProductStock {
    /** 商品在庫ID */
    private Integer id;
    /** 商品ID */
    private Integer productId;
    /** 在庫量 */
    private Integer stock;
}
