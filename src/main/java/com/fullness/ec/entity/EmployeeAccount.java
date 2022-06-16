package com.fullness.ec.entity;

import lombok.Data;

/**
 * 担当者アカウントテーブルエンティティ
 */
@Data
public class EmployeeAccount {
    /** 担当者アカウントID */
    private Integer id;
    /** アカウント名 */
    private String name;
    /** パスワード */
    private String password;
    /** 社員エンティティ */
    private Employee employee;
}
