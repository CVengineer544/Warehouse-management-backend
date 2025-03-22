package com.example.shopping.demos.web.mapper;

import com.example.shopping.demos.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<User> findUsersByUseful(@Param("useful") int useful);

    void updateUserUseful(@Param("id") Long id, @Param("useful") int useful);

    List<User> findAllUsers();

    void updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    void deleteUser(@Param("id") Long id);
    void addUser(User user);
    void updateUserType(@Param("id") Long id, @Param("newType") Integer newType);
    User findById(@Param("id") Long id);
    void updateUser(User user);
}