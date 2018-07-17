package com.test.Actuator.testActuator.services;

import org.springframework.stereotype.Service;

import com.test.Actuator.testActuator.configuration.ApplicationProperties;

@Service
public class CalculationService {

	private final ApplicationProperties applicationProperties;

	public CalculationService(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	public int add(int x, int y) {
		return x + y;
	}
	
	public String solveAdd(int x, int y) {
		return applicationProperties.getAnswerText() + add(x,y);
	}
}
