package com.example.shopping.demos.web.controller;

import com.example.shopping.demos.web.entity.TransferRecord;
import com.example.shopping.demos.web.service.TransferRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfer-records")
public class TransferRecordController {

    @Autowired
    private TransferRecordService transferRecordService;

    @GetMapping("/all") // 修改为 /all
    public List<TransferRecord> fetchAllTransferRecords() {
        System.out.println("获取转移记录请求到达"); // 调试信息
        return transferRecordService.getAllTransferRecords(); // 获取所有转移记录
    }
}