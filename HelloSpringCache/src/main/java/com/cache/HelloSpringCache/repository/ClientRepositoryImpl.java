package com.cache.HelloSpringCache.repository;

import java.text.MessageFormat;
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

	private Map<String, Client> data;

	public ClientRepositoryImpl() {
		data = new HashMap<>();
	}

	@Override
	public void loadData(Map<String,Client> data) {
		this.data = data;
	}
	
	@Override
	public Client getClientById(String clientId) {
		log.info(MessageFormat.format("getClientId:{0}", clientId));
		Client client = data.get(clientId);
		simulateSlowService();
		return client;
	}

	@Override
	public List<Client> getAllClients() {
		log.info("getAllClients");
		List<Client> values = data.values().stream().collect(Collectors.toList());
		simulateSlowService();
		return values;
	}

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

	@Override
	public String createClient(Client client) {
		data.put(client.getFirstName(),client);
		return client.toString();
	}
}
