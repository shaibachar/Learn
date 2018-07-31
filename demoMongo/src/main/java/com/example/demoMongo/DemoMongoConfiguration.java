package com.example.demoMongo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demoMongo.domain.Client;
import com.example.demoMongo.repository.ClientRepository;

import reactor.core.publisher.Flux;

@Configuration
public class DemoMongoConfiguration {

	@Bean
	public ApplicationRunner demoData(ClientRepository clientRepository) {

		return args -> {
			clientRepository.deleteAll()
					.thenMany(Flux.just("shalom", "shai", "yosef")
							.map(Client::new)
							.flatMap(clientRepository::save))
					.thenMany(clientRepository.findAll())
					.subscribe(System.out::println);
		};
	}
}
