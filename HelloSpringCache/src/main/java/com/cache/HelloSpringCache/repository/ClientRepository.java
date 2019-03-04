package com.cache.HelloSpringCache.repository;

import java.util.List;
import java.util.Map;

import com.cache.HelloSpringCache.model.Client;

public interface ClientRepository {

	public List<Client> getClientById(String clientId);
	
	public Map<String, List<Client>> getAllClients();

	public String createClient(Client client);
	void loadData(Map<String, List<Client>>  data);
}
