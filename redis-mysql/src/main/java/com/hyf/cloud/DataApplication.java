package com.hyf.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hyf.cloud.mapper")
@SpringBootApplication
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class,args);
    }
}
