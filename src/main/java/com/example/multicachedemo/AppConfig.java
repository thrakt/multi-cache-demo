package com.example.multicachedemo;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class AppConfig {

    private CachedComponent cachedComponent;

    public AppConfig(CachedComponent cachedComponent) {
        this.cachedComponent = cachedComponent;
    }

    @Bean
    @Primary
    public CacheManager cacheManagerA() {
        CaffeineCacheManager cm = new CaffeineCacheManager();
        cm.setCaffeine(Caffeine.newBuilder().maximumSize(1L)
                               .expireAfterWrite(
                                       Duration.ofSeconds(
                                               10L)));
        return cm;
    }

    @Bean
    public CacheManager cacheManagerB() {
        CaffeineCacheManager cm = new CaffeineCacheManager();
        cm.setCaffeine(Caffeine.newBuilder().maximumSize(1L)
                               .expireAfterWrite(
                                       Duration.ofSeconds(
                                               30L)));
        return cm;
    }

    @Scheduled(fixedRate = 1000L)
    public void task() {
        cachedComponent.usingCacheA();
        cachedComponent.usingCacheB();
    }
}
