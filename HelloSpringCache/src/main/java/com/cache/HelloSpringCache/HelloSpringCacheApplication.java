package com.cache.HelloSpringCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cache.HelloSpringCache.configuration.ApplicationProperties;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableScheduling
public class HelloSpringCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringCacheApplication.class, args);
	}

}
