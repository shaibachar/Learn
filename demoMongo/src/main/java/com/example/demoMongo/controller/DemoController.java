package com.example.demoMongo.controller;

import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.example.demoMongo.domain.Client;
import com.example.demoMongo.services.ClientService;

@Configuration
public class DemoController {

	@Bean
	RouterFunction<?> routerFunction(ClientService clientService){
		return route(GET("/clients"),	req -> ok().body(clientService.getAllClients(),Client.class))
				           .andRoute(GET("/client/{phoneNumber}"), req -> ok().body(clientService.getClientByPhone(req.pathVariable("phoneNumber")),Client.class));
	}
	
}
