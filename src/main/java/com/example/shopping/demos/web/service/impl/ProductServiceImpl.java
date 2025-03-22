package com.example.shopping.demos.web.service.impl;

import com.example.shopping.demos.web.entity.Product;
import com.example.shopping.demos.web.entity.TransferRecord;
import com.example.shopping.demos.web.entity.Warehouse;
import com.example.shopping.demos.web.mapper.ProductMapper;
import com.example.shopping.demos.web.mapper.TransferRecordMapper; // 确保导入 TransferRecordMapper
import com.example.shopping.demos.web.mapper.WarehouseMapper;
import com.example.shopping.demos.web.service.ProductService;
import com.example.shopping.demos.web.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime; // 导入 LocalDateTime
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private TransferRecordMapper transferRecordMapper; // 注入 TransferRecordMapper

    @Autowired
    private JwtUtil jwtUtil; // 注入 JwtUtil

    @Override
    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    @Override
    public String getWarehouseNameByProductId(int productId) {
        return productMapper.getWarehouseNameByProductId(productId); // 调用 Mapper 方法
    }

    @Override
    public Product addProduct(Product product) {
        // 如果前端只传递了 warehouse_id，将其映射为 Warehouse 对象
        if (product.getWarehouse() == null && product.getCurrentWarehouseId() > 0) {
            Warehouse warehouse = new Warehouse();
            warehouse.setId(product.getCurrentWarehouseId());
            product.setWarehouse(warehouse);
        }

        // 调用 Mapper 层插入数据
        productMapper.insert(product);
        return product;
    }

    @Override
    public void deleteProduct(int id) {
        transferRecordMapper.deleteByProductId(id);
        productMapper.delete(id);
    }

    @Override
    public Product findById(int id) {
        return productMapper.findById(id);
    }

    @Override
    public String getWarehouseNameByWarehouseId(Long warehouseId) { // 修改为根据 warehouseId 获取
        Warehouse warehouse = warehouseMapper.findById(warehouseId); // 根据 ID 查询仓库
        return warehouse != null ? warehouse.getName() : null; // 返回仓库名称或 null
    }

    @Override
    public String updateProductWarehouse(int productId, Long newWarehouseId, int quantity, HttpServletRequest request) {
        // 查询产品信息
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new RuntimeException("产品不存在");
        }

        // 获取旧仓库 ID
        Long oldWarehouseId = product.getCurrentWarehouseId();

        // 更新产品的当前仓库 ID
        product.setCurrentWarehouseId(newWarehouseId);

        // 查询新仓库对象
        Warehouse newWarehouse = warehouseMapper.findById(newWarehouseId);
        if (newWarehouse == null) {
            throw new RuntimeException("目标仓库不存在");
        }

        // 更新产品关联的仓库
        product.setWarehouse(newWarehouse);

        // 保存到数据库
        productMapper.update(product);

        // 从请求头中获取 JWT
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去掉 "Bearer " 前缀
        } else {
            throw new RuntimeException("无效的授权头");
        }

        // 从 JWT 中提取用户名
        String currentUser = jwtUtil.extractUsername(token);
        if (currentUser == null) {
            throw new RuntimeException("无效的 token");
        }

        // 记录仓库转移日志，传入库存数量
        recordTransfer(productId, oldWarehouseId, newWarehouseId, currentUser, quantity);

        // 返回更新结果
        return String.format("产品 ID: %d 已从仓库 ID %d 转移到仓库 ID %d，目标仓库名称: %s",
                productId, oldWarehouseId, newWarehouseId, newWarehouse.getName());
    }

    @Override
    public void recordTransfer(int productId, Long fromWarehouseId, Long toWarehouseId, String operator, int quantity) {
        TransferRecord transferRecord = new TransferRecord();
        transferRecord.setProductId(productId);
        transferRecord.setFromWarehouseId(fromWarehouseId);
        transferRecord.setToWarehouseId(toWarehouseId);
        transferRecord.setOperator(operator); // 设置操作人
        transferRecord.setTransferTime(LocalDateTime.now()); // 设置当前时间为 LocalDateTime
        transferRecord.setQuantity(quantity); // 设置转移数量

        // 调用 Mapper 方法插入转移记录
        transferRecordMapper.insert(transferRecord);
    }
}