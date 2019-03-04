package com.cache.HelloSpringCache.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cache.HelloSpringCache.model.Client;
import com.cache.HelloSpringCache.service.ClientService;

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
	
	
	@PostMapping("createClient")
	public ResponseEntity<String>  createClient(@ModelAttribute Client client) {
		String res = clientService.createClient(client);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
}
