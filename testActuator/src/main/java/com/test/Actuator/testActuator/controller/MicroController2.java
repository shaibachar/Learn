package com.test.Actuator.testActuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroController2 {

	@GetMapping("/service2")
	public String service2() {
		return "Hello from service";
	}
}
