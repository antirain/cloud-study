package com.hyf.cloud.cache;

public interface UseTokenCache extends UseCache {

    String getCache();

    String getCache(String key);

}
