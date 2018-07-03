package com.pactms1;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import au.com.dius.pact.consumer.ConsumerPactBuilder.PactDslWithProvider;
import au.com.dius.pact.consumer.ConsumerPactTest;
import au.com.dius.pact.model.PactFragment;

public class Service2PactTest extends ConsumerPactTest {

	@Override
	protected PactFragment createFragment(PactDslWithProvider builder) {
		return builder
				.uponReceiving("Request for service 2")
					.path("/service2")
					.method("GET")
				.willRespondWith()
					.status(200)
					.body("Hello from service")
				.toFragment();
	}

	@Override
	protected String providerName() {
		return "Service 2";
	}

	@Override
	protected String consumerName() {
		return "Service 1";
	}

	@Override
	protected void runTest(String url) throws IOException {
		Service2Client client = new Service2Client(url);
		String response = client.callService2();
		assertEquals("Hello from service", response);
	}

}
