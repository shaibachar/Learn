package com.firebaseTest;

import com.firebaseTest.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class FirebaseTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirebaseTestApplication.class, args);
	}

}
