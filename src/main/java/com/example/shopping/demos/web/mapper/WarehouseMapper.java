package com.example.shopping.demos.web.mapper;

import com.example.shopping.demos.web.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseMapper {

    List<Warehouse> findAll(); // 查询所有仓库

    void insert(Warehouse warehouse); // 插入新仓库

    void delete(Long id); // 删除仓库

    Warehouse findByName(String name); // 根据名称查询仓库

    Warehouse findById(Long id); // 根据 ID 查询仓库
}