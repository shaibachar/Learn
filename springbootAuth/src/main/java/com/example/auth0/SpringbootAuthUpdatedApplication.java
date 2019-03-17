package com.example.auth0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.auth0.config.SecurityProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityProperties.class})
public class SpringbootAuthUpdatedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAuthUpdatedApplication.class, args);
	}
}
