package com.said.spring_cache_location_finder.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheConfig {

    private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);
    private final CacheManager cacheManager;

    public CacheConfig(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void logCacheContents() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            Cache cache = cacheManager.getCache(cacheName);
            logger.info("Cache {}", cache.getNativeCache());
        });
    }
}
