package com.cache.HelloSpringCache.service;

import java.util.List;
import java.util.Map;

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
	public List<Client> getClient(String clientId) {
		List<Client> clientById = clientRepository.getClientById(clientId);
		return clientById;
	}

	@Cacheable("clients")
	public Map<String, List<Client>> getAllClient() {
		Map<String, List<Client>> allClients = clientRepository.getAllClients();
		return allClients;
	}

}
