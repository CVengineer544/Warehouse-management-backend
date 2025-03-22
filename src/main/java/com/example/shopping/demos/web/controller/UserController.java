package com.example.shopping.demos.web.controller;

import com.example.shopping.demos.web.entity.User;
import com.example.shopping.demos.web.service.UserService;
import com.example.shopping.demos.web.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    // 登录方法
    // UserController.java
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user, HttpServletRequest request) {
        User foundUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (foundUser != null) {
            // 检查用户的可用性
            if (foundUser.getUseful() == 0) { // 假设 1 表示用户被禁用
                throw new RuntimeException("您已被封禁"); // 返回封禁提示
            }

            String token = jwtUtil.generateToken(foundUser.getUsername(), foundUser.getType(), foundUser.getUseful()); // 生成 JWT
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", foundUser.getId());
            response.put("type", foundUser.getType());

            // 将用户名存储在请求属性中
            request.setAttribute("username", foundUser.getUsername());

            System.out.println("token: " + token);
            return response;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build(); // 如果用户不存在，返回 404
        }
    }
    // 更新用户信息
    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        userService.updateUser(user); // 假设你已经在 UserService 中实现了这个方法
        return ResponseEntity.ok().build();
    }
    // 获取启用用户
    @GetMapping("/enabled")
    public List<User> getEnabledUsers() {
        return userService.findUsersByUseful(0);
    }

    // 启用用户
    @PostMapping("/enable/{id}")
    public void enableUser(@PathVariable Long id) {
        userService.updateUserUseful(id, 1);
    }

    // 获取禁用用户
    @GetMapping("/disabled")
    public List<User> getDisabledUsers() {
        return userService.findUsersByUseful(1);
    }

    // 禁用用户
    @PostMapping("/disable/{id}")
    public void disableUser(@PathVariable Long id) {
        userService.updateUserUseful(id, 0);
    }

    // 获取所有用户
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // 重置密码
    @PostMapping("/reset-password/{id}")
    public void resetPassword(@PathVariable Long id, @RequestBody String newPassword) {
        userService.resetPassword(id, newPassword);
    }

    // 删除用户
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }
    @PostMapping("/change-type/{id}")
    public ResponseEntity<Void> changeUserType(@PathVariable Long id, @RequestBody Map<String, Integer> requestBody) {
        Integer newType = requestBody.get("newType"); // 从请求体中获取 newType
        userService.updateUserType(id, newType); // 调用 Service 方法
        return ResponseEntity.ok().build(); // 返回成功响应
    }
}