package com.cache.HelloSpringCache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CachingService {

	@Autowired
	private CacheManager cacheManager;
	 
	public void evictSingleCacheValue(String cacheName, String cacheKey) {
	    cacheManager.getCache(cacheName).evict(cacheKey);
	}
	 
	public void evictAllCacheValues(String cacheName) {
	    cacheManager.getCache(cacheName).clear();
	}
	
	public void evictAllCaches() {
	    cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
}
