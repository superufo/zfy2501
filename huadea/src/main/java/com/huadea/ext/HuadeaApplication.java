package com.huadea.ext;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.huadea.ext","com.huadea.ext.config.**","com.huadea.ext.dto.**"})
@MapperScan(basePackages = "com.huadea.ext.**.*mapper")
@Slf4j
public class HuadeaApplication {
    public static void main(String[] args) {
        SpringApplication.run(HuadeaApplication.class, args);
    }
}
