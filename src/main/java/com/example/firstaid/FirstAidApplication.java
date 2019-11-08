package com.example.firstaid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan(basePackages = "com.example.firstaid.mapper")
public class FirstAidApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstAidApplication.class, args);
    }

}
