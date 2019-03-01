package com.cache.HelloSpringCache.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cache.HelloSpringCache.model.Client;
import com.cache.HelloSpringCache.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/client/")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("getClient/{clientId}")
	public List<Client> getClient(@PathVariable("clientId") String clientId) {
		List<Client> client = clientService.getClient(clientId);
		return client;
	}

	@GetMapping("getAllClient")
	public Map<String, List<Client>> getAllClient() {
		Map<String, List<Client>> allClient = clientService.getAllClient();
		return allClient;
	}
}
