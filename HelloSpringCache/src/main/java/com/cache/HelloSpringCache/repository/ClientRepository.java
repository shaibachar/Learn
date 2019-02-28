package com.cache.HelloSpringCache.repository;

import java.util.List;
import java.util.Map;

import com.cache.HelloSpringCache.model.Client;

public interface ClientRepository {

	public Client getClientById(String clientId);
	
	public List<Client> getAllClients();

	void loadData(Map<String, Client> data);
}
