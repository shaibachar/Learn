package com.test.services;

import com.test.configuration.ApplicationProperties;

public class HelloWorldServiceImpl implements HelloWorldService {

	private final ApplicationProperties applicationProperties;

	public HelloWorldServiceImpl(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	/* (non-Javadoc)
	 * @see com.test.services.HelloWorldService#sayHello(java.lang.String)
	 */
	@Override
	public String sayHello(String name) {
		return applicationProperties.getMessage() + " " + name;
	}

}
