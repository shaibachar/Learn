package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.domain.Client;
import com.example.repository.ClientRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;

@Service
public class ClientService {

	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@GraphQLQuery(name = "clients")
	public List<Client> getClients() {
		return clientRepository.findAll();
	}

	@GraphQLQuery(name = "client")
	public Optional<Client> getClientById(@GraphQLArgument(name = "id") Long id) {
		return clientRepository.findById(id);
	}

	@GraphQLMutation(name = "saveClient")
	public Client saveClient(@GraphQLArgument(name = "client") Client client) {
		return clientRepository.save(client);
	}

	@GraphQLMutation(name = "deleteClient")
	public void deleteClient(@GraphQLArgument(name = "id") Long id) {
		clientRepository.deleteById(id);
	}

	@GraphQLQuery(name = "isChild")
	public boolean isCool(@GraphQLContext Client client) {
		return client.getAge() < 18;
	}


}
