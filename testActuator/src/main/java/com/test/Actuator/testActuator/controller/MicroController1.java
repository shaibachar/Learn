package com.test.Actuator.testActuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Actuator.testActuator.Service2Client;

@RestController
public class MicroController1 {

	@GetMapping("/service1")
	public String service1() {
		Service2Client client = new Service2Client("http://localhost:8080/");
		String response = client.callService2();
		return "service2: " +  response;
	}
}
