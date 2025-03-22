package com.example.shopping.demos.web.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Data
@Entity
@Table(name = "xmut_product")
public class Product {

    @Id
    private int id; // 使用 int 类型作为主键

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private Double price; // 使用 Double 类型来表示价格

    @Column(nullable = false, length = 255)
    private String image; // 存储图片文件名或路径

    @Column(nullable = false)
    private Integer stock; // 库存数量

    @Column(nullable = false, length = 100)
    private String category; // 物品类别

    @ManyToOne // 多个产品对应一个仓库
    @JoinColumn(name = "warehouse_id", nullable = true) // 关联的外键
    private Warehouse warehouse; // 关联的仓库对象

    @Column(name = "current_warehouse_id", nullable = true)
    private Long currentWarehouseId;
}