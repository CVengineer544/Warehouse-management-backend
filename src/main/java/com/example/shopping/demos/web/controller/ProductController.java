package com.example.shopping.demos.web.controller;

import com.example.shopping.demos.web.entity.Product;
import com.example.shopping.demos.web.entity.TransferRequest;
import com.example.shopping.demos.web.mapper.ProductMapper;
import com.example.shopping.demos.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/all") // 获取所有产品
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}/warehouse") // 根据产品ID获取仓库名称
    public String getWarehouseNameByProductId(@PathVariable int id) {
        String warehouseName = productService.getWarehouseNameByProductId(id); // 获取仓库名称
        return warehouseName != null ? warehouseName : "仓库未找到"; // 返回仓库名称或默认消息
    }

    @PostMapping("/add") // 添加产品
    public Product addProduct(@RequestBody Product product) {
        System.out.println("接收到的产品数据：" + product);
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}") // 删除产品
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/update-warehouse") // 更新产品仓库
    public String updateProductWarehouse(@RequestBody TransferRequest transferRequest, HttpServletRequest request) {
        int productId = transferRequest.getProductId();
        long newWarehouseId = transferRequest.getNewWarehouseId();
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new RuntimeException("产品不存在");
        }
        int quantity = product.getStock();
        return productService.updateProductWarehouse(productId, newWarehouseId, quantity, request);
    }
}