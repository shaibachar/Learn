package com.hello.HelloRestAssured.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.hello.HelloRestAssured.service.HelloService;

@Profile("test")
@Configuration
public class TestApplicationConfig {

	@Bean
	@Primary
	public HelloService HelloService() {
		return Mockito.mock(HelloService.class);
	}
}
