package com.fullness.ec.entity;

import lombok.Data;

/**
 * 社員テーブル用エンティティ
 */
@Data
public class Employee {
    /** 社員ID */
    private Integer id;
    /** 社員名 */
    private String name;
    /** 社員名カナ */
    private String nameKana;
    /** 部署ID */
    private Integer departmentId;
}
