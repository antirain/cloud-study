package com.hyf.consumer.controller;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.CloudFlux;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RefreshScope
@RestController
public class ProviderController {
    @Value("${server.port}")
    String port;
    @Value("${stuff.age}")
    String age;
    @Value("${stuff.gender}")
    String gender;

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "forezp",required = false) String name) throws InterruptedException {
        Thread.sleep(3000);
        return "hello " + name + ", i'm provider ,my port:" + port;
    }
    @GetMapping("/get")
    public String get(){
        return "age = " + age + ",gender = " + gender;
    }


}
