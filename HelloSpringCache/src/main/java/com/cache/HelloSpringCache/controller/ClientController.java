package com.cache.HelloSpringCache.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.websocket.server.PathParam;

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
	public Client getClient(@PathVariable("clientId") String clientId) {
		log.info(MessageFormat.format("getClientId:{0}", clientId));
		Client clientById = clientService.getClient(clientId);
		return clientById;
	}
	
	
	@GetMapping("getAllClient")
	public List<Client> getAllClient() {
		List<Client> allClients = clientService.getAllClient();
		return allClients;
	}
}
