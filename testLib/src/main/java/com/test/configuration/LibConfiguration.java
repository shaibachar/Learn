package com.test.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.services.HelloWorldService;
import com.test.services.HelloWorldServiceImpl;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
@ConditionalOnClass(HelloWorldService.class)
public class LibConfiguration {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Bean
	@ConditionalOnMissingBean
	public HelloWorldService getHelloWorldService() {
		return new HelloWorldServiceImpl(applicationProperties);
	}
}
