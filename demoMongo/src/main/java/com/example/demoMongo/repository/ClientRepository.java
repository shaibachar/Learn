package com.example.demoMongo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demoMongo.domain.Client;

import reactor.core.publisher.Mono;

public interface ClientRepository extends ReactiveCrudRepository<Client, String> {
	
	Mono<Client> findByPhoneNumber(String phoneNumber);
}
