package com.cache.HelloSpringCache.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="application")
public class ApplicationProperties {

	private String cacheFilePath;
	private boolean useLocalCache;

	public String getCacheFilePath() {
		return cacheFilePath;
	}

	public boolean useLocalCache() {
		return useLocalCache;
	}

	public boolean isUseLocalCache() {
		return useLocalCache;
	}

	public void setUseLocalCache(boolean useLocalCache) {
		this.useLocalCache = useLocalCache;
	}

	public void setCacheFilePath(String cacheFilePath) {
		this.cacheFilePath = cacheFilePath;
	}
	
	
	

}
