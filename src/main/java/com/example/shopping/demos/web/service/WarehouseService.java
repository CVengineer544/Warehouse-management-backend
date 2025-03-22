package com.example.shopping.demos.web.service;

import com.example.shopping.demos.web.entity.Warehouse;
import com.example.shopping.demos.web.mapper.TransferRecordMapper;
import com.example.shopping.demos.web.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.shopping.demos.web.mapper.WarehouseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private TransferRecordMapper transferRecordMapper;
    public List<Warehouse> getAllWarehouses() {
        return warehouseMapper.findAll();
    }

    public Warehouse addWarehouse(Warehouse warehouse) {
        warehouseMapper.insert(warehouse);
        return warehouse;
    }

    public void deleteWarehouse(Long id) {
        transferRecordMapper.deleteByWarehouseId(id);
        warehouseMapper.delete(id);
    }

    public Warehouse findByName(String name) {
        return warehouseMapper.findByName(name); // 调用 XML 中的查询
    }

    public Warehouse findById(Long id) {
        return warehouseMapper.findById(id); // 调用 XML 中的查询
    }
}