package com.example.shopping.demos.web.service;

import com.example.shopping.demos.web.entity.User;
import com.example.shopping.demos.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByUsernameAndPassword(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }

    public List<User> findUsersByUseful(int useful) {
        return userMapper.findUsersByUseful(useful);
    }

    public void updateUserUseful(Long id, int useful) {
        userMapper.updateUserUseful(id, useful);
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public void resetPassword(Long id, String newPassword) {
        userMapper.updatePassword(id, newPassword);
    }

    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }
    public void addUser(User user) {
        userMapper.addUser(user);
    }
    public void updateUserType(Long id, Integer newType) {
        userMapper.updateUserType(id, newType); // 调用 Mapper 方法更新用户权限
    }
    public User findById(Long id) {
        return userMapper.findById(id);
    }
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}