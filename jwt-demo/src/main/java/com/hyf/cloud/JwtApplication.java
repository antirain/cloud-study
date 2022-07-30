package com.hyf.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.hyf.cloud.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class,args);
    }
}
