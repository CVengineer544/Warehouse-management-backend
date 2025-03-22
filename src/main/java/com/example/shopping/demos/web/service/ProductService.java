package com.example.shopping.demos.web.service;

import com.example.shopping.demos.web.entity.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {
    String getWarehouseNameByWarehouseId(Long warehouseId); // 修改为根据 warehouseId 获取
    List<Product> getAllProducts();
    Product addProduct(Product product);
    void deleteProduct(int id);
    Product findById(int id);
    String getWarehouseNameByProductId(int productId);
    String updateProductWarehouse(int productId, Long newWarehouseId, int quantity, HttpServletRequest request);

    void recordTransfer(int productId, Long fromWarehouseId, Long toWarehouseId, String operator, int quantity);
}