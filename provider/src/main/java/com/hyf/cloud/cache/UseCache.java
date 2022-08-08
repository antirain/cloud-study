package com.hyf.cloud.cache;

public interface UseCache {

    void putCache(String key,Object value,long time);

    void putCache(Object value,long time);

    String getKey();

    String getCache();

    String getCache(String key);

}
