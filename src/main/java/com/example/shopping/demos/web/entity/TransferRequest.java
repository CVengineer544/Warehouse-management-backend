package com.example.shopping.demos.web.entity;

import lombok.Data;

@Data
public class TransferRequest {
    private int productId;
    private Long newWarehouseId;
}