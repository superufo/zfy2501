package com.huadea.ext;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder() {
        // 原始密码
        String rawPassword = "123456";
        // 生成密文
        String encodedPassword = passwordEncoder.encode(rawPassword);
        log.info("encodedPassword:{}",encodedPassword);

        // 验证密文
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword), "密码匹配验证失败");

        // 验证错误密码
        assertFalse(passwordEncoder.matches("wrongPassword", encodedPassword), "错误密码验证失败");
    }
}
