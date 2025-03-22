package com.example.shopping.demos.web.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "xmut_warehouse")
public class Warehouse {

    @Id
    private long id; // 使用 long 类型作为主键

    @Column(nullable = false, length = 255)
    private String name; // 仓库名称
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products; // 仓库下的产品列表（与 Product 关联）

}