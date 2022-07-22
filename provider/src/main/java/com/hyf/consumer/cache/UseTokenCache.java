package com.hyf.consumer.cache;

public interface UseTokenCache extends UseCache {

    String getCache();

    String getCache(String key);

}
