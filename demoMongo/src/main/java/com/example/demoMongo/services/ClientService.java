package com.example.demoMongo.services;

import org.springframework.stereotype.Service;

import com.example.demoMongo.domain.Client;
import com.example.demoMongo.domain.QClient;
import com.example.demoMongo.repository.ClientRepository;
import com.querydsl.core.types.Predicate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public Flux<Client> getAllClients(){
		return clientRepository.findAll();
	}
	
	public Mono<Client> getClientByPhone(String phoneNumber){
		return clientRepository.findByPhoneNumber(phoneNumber);
	}
	
}
