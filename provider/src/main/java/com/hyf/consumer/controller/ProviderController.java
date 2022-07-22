package com.hyf.consumer.controller;

import cn.hutool.json.JSONUtil;
import com.hyf.consumer.cache.UseCache;
import com.hyf.consumer.cache.UseTokenCache;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.CloudFlux;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
@Slf4j
@RefreshScope
@RestController
public class ProviderController {
    @Value("${server.port}")
    String port;
    @Value("${stuff.age}")
    String age;
    @Value("${stuff.gender}")
    String gender;

    @Autowired
    private UseTokenCache tokenCache;
    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "forezp",required = false) String name) throws InterruptedException {
        Thread.sleep(3000);
        return "hello " + name + ", i'm provider ,my port:" + port;
    }
    @GetMapping("/get")
    public String get(){
        return "age = " + age + ",gender = " + gender;
    }

    @GetMapping("/getCache")
    public String getCache(@RequestParam String key){
        return tokenCache.getCache(key);
    }

    @PostMapping("/putCache")
    public String putCache(@RequestParam String key,@RequestParam Object value){
        log.debug("新增缓存");
        tokenCache.putCache(key,value,3600);
        return null;
    }
}
