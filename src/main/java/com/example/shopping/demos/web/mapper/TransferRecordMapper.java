package com.example.shopping.demos.web.mapper;

import com.example.shopping.demos.web.entity.TransferRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransferRecordMapper {
    void insert(TransferRecord transferRecord);
    List<TransferRecord> findAll();
    void deleteByProductId(int productId);
    void deleteByWarehouseId(Long warehouseId);
}