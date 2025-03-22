package com.example.shopping.demos.web.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CaptchaController {
    @Autowired
    private Producer captchaProducer;

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 使用 request.getSession() 确保获取 session
        HttpSession session = request.getSession(true);

        String captchaText = captchaProducer.createText();
        BufferedImage captchaImage = captchaProducer.createImage(captchaText);

        // 调试输出
        System.out.println("Generated Captcha: " + captchaText);

        // 确保存储验证码
        session.setAttribute("captcha", captchaText);

        // 设置响应类型
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // 将图像写入响应输出流
        javax.imageio.ImageIO.write(captchaImage, "jpg", response.getOutputStream());
        response.getOutputStream().close();
    }

    @PostMapping("/captcha/verify")
    public boolean verifyCaptcha(@RequestParam String captcha, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        String storedCaptcha = (String) session.getAttribute("captcha");

        System.out.println("Stored Captcha: " + storedCaptcha);
        System.out.println("User Input Captcha: " + captcha);

        return storedCaptcha != null && storedCaptcha.equalsIgnoreCase(captcha);
    }
}