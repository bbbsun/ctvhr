package com.bbbsun.ctvhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.bbbsun.ctvhr.mapper")
public class CtvhrApplication {

    public static void main(String[] args) {
        SpringApplication.run(CtvhrApplication.class, args);
    }

}
