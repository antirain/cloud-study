package com.hyf.consumer.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hyf.consumer.cache.UseTokenCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/post")
    public JSONObject get(@RequestParam String qyid){
        Assert.hasText(qyid,"QYID为空");
        return JSONUtil.createObj().putOnce("result","fail");
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
