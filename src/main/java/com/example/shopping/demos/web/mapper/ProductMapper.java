package com.example.shopping.demos.web.mapper;

import com.example.shopping.demos.web.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<Product> findAll(); // 查询所有货品

    void insert(Product product); // 插入新货品

    void delete(int id); // 删除货品

    Product findById(int id); // 根据 ID 查询货品

    void update(Product product); // 更新货品信息
    String getWarehouseNameByProductId(int productId);
}