package com.example.shopping.demos.web.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "xmut_transfer_record")
public class TransferRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int productId;
    private Long fromWarehouseId;
    private Long toWarehouseId;
    private String operator; // 操作人
    private LocalDateTime transferTime; // 操作时间
    private int quantity;
}