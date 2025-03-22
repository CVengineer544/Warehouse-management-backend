package com.example.shopping.demos.web.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private String secret = "your_secret_key"; // 从配置文件中读取
    private long expiration = 3600000; // 1 hour

    // 生成 JWT，包含用户名和用户类型
    // JwtUtil.java
    public String generateToken(String username, int userType, int useful) {
        return Jwts.builder()
                .setSubject(username) // 设置用户名为主题
                .claim("type", userType) // 添加用户类型到 JWT
                .claim("useful", useful) // 添加用户可用性到 JWT
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // 从 JWT 中提取用户名
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 提取所有声明
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // 检查 JWT 是否过期
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 验证 JWT
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}