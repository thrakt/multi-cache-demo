package com.example.multicachedemo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CachedComponent {

    @Cacheable(value = "usingCacheA", cacheManager = "cacheManagerA")
    public String usingCacheA() {
        System.out.println("call getting value for cache A");
        return "usingCacheA";
    }

    @Cacheable(value = "usingCacheB", cacheManager = "cacheManagerB")
    public String usingCacheB() {
        System.out.println("call getting value for cache B");
        return "usingCacheB";
    }

}
