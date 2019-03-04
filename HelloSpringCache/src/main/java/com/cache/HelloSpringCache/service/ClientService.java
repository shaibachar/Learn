package com.cache.HelloSpringCache.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cache.HelloSpringCache.model.Client;
import com.cache.HelloSpringCache.repository.ClientRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Cacheable("clientById")
	public Client getClient(String clientId) {
		Client clientById = clientRepository.getClientById(clientId);
		return clientById;
	}

	@Cacheable("clients")
	public List<Client> getAllClient() {
		List<Client> allClients = clientRepository.getAllClients();
		return allClients;
	}

	public String createClient(Client client) {
		String res = clientRepository.createClient(client);
		return res;
	}

}
