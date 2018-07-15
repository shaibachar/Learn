package com.test.Actuator.testActuator;

import org.springframework.web.client.RestTemplate;

public class Service2Client {

	private String baseURL;

	public Service2Client(String baseURL) {
		super();
		this.baseURL = baseURL;
	}

	public String callService2() {
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(baseURL + "/service2", String.class);
		return response;

	}

}
