package com.test.Actuator.testActuator.services;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

	public String sayHello(String name) {
		return "Hello " + name;
	}
}
