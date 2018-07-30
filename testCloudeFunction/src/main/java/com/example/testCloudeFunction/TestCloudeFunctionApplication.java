package com.example.testCloudeFunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.internal.Function;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class TestCloudeFunctionApplication {

	@Bean
	public Function<Flux<String>, Flux<String>> uppercase() {
		return flux -> flux.map(value -> value.toUpperCase());
	}

	public static void main(String[] args) {
		SpringApplication.run(TestCloudeFunctionApplication.class, args);
	}
}
