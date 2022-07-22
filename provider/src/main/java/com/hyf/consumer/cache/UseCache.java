package com.hyf.consumer.cache;

public interface UseCache {

    void putCache(String key,Object value,long time);

    void putCache(Object value,long time);

    String getKey();

    Object getCache();

    Object getCache(String key);

}
