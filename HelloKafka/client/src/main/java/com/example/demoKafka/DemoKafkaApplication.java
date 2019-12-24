package com.example.demoKafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoKafkaApplication {

	private final Logger logger = LoggerFactory.getLogger(DemoKafkaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemoKafkaApplication.class, args);
	}
}
