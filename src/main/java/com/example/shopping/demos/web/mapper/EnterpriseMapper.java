package com.example.shopping.demos.web.mapper;

import com.example.shopping.demos.web.entity.Enterprise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EnterpriseMapper {
    // 查询所有企业信息
    @Select("SELECT * FROM xmut_enterprise_info")
    List<Enterprise> findAll();
}