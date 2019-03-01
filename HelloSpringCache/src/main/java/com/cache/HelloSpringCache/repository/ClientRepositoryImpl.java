package com.cache.HelloSpringCache.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cache.HelloSpringCache.model.Client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClientRepositoryImpl implements ClientRepository {

	private Map<String, List<Client>> data;

	public ClientRepositoryImpl() {
		data = new HashMap<>();
	}

	@Override
	public void loadData(Map<String, List<Client>> data) {
		this.data = data;
	}

	@Override
	public List<Client>  getClientById(String clientId) {
		List<Client> list = data.get(clientId);
		return list;
	}

	@Override
	public Map<String, List<Client>> getAllClients() {
		return data;
	}

	private void simulateSlowService() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
