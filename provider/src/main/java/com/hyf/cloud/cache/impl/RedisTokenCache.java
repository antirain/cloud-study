package com.hyf.cloud.cache.impl;

import com.hyf.cloud.cache.UseTokenCache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Service
public class RedisTokenCache implements UseTokenCache {

    private final StringRedisTemplate stringRedisTemplate;
    private final static String APP_TOKEN_KEY = "APP_TOKEN";

    @Override
    public void putCache(String key, Object value, long time) {
        key = key != null ? (getKey() + ":" + key) : getKey();
        stringRedisTemplate.opsForValue().set(key, (String) value, 3600, TimeUnit.SECONDS);
    }

    @Override
    public void putCache(Object value, long time) {
        putCache(null, value, time);
    }

    @Override
    public String getKey() {
        return APP_TOKEN_KEY;
    }

    @Override
    public String getCache() {
        return getCache(null);
    }

    @Override
    public String getCache(String key) {
        key = key != null ? (getKey() + ":" + key) : getKey();
        return stringRedisTemplate.opsForValue().get(key);
    }
}
