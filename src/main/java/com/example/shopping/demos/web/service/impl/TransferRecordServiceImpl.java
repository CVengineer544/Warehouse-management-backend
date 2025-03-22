package com.example.shopping.demos.web.service.impl;

import com.example.shopping.demos.web.entity.TransferRecord;
import com.example.shopping.demos.web.mapper.TransferRecordMapper;
import com.example.shopping.demos.web.service.TransferRecordService;
import com.example.shopping.demos.web.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TransferRecordServiceImpl implements TransferRecordService {
    private static final Logger logger = LoggerFactory.getLogger(TransferRecordServiceImpl.class);

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TransferRecordMapper transferRecordMapper;
    @Autowired
    private JwtUtil jwtUtil; // 注入 JwtUtil

    @Override
    public List<TransferRecord> getAllTransferRecords() {
        List<TransferRecord> records = transferRecordMapper.findAll(); // 获取所有转移记录
        logger.info("获取到的转移记录: {}", records); // 打印获取到的转移记录
        return records; // 返回转移记录
    }


}