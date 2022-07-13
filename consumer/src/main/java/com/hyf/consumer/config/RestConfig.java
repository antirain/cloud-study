package com.hyf.consumer.config;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig
{
    /**
     * 创建restTemplate对象。
     * LoadBalanced注解表示赋予restTemplate使用Ribbon的负载均衡的能力（一定要加上注解，否则无法远程调用）
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
