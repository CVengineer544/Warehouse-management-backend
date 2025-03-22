package com.example.shopping.demos.web.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "xmut_users")
public class User {

    @Id
    private String id;

    @Column(nullable = false, unique = true, length = 50)
    private String account;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    private Integer sex;

    private Integer type;

    // 新增的字段
    @Column(nullable = true)
    private Integer useful;

    // 其他字段
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}