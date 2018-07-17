package com.test.Actuator.testActuator.services;

import org.springframework.stereotype.Service;

import com.test.Actuator.testActuator.configuration.ApplicationProperties;

@Service
public class HelloWorldService {

	private final CalculationService calculationService;
	private final ApplicationProperties applicationProperties;

	public HelloWorldService(ApplicationProperties applicationProperties,CalculationService calculationService) {
		this.applicationProperties = applicationProperties;
		this.calculationService = calculationService;
	}

	public String sayHello(String name) {
		return applicationProperties.getMessage() + " " + name;
	}
	
	public String wrappCalc(int x,int y) {
		return calculationService.solveAdd(x, y);
	}
}
