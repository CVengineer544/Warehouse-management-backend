package com.example.shopping.demos.web.service;

import com.example.shopping.demos.web.entity.Enterprise;
import com.example.shopping.demos.web.mapper.EnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    // 获取所有企业信息
    public List<Enterprise> getAllEnterprises() {
        return enterpriseMapper.findAll();
    }
}