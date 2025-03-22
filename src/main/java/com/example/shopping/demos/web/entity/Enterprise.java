package com.example.shopping.demos.web.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "xmut_enterprise_info")
public class Enterprise {
    @Id
    private Long id; // 企业ID
    private String enterpriseName; // 企业名称
    private String address; // 具体地址
    private String contactPerson; // 联系人
    private String contactNumber; // 联系方式
    private LocalDateTime createdAt; // 创建时间
    private LocalDateTime updatedAt; // 更新时间

}