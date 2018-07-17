package com.test.Actuator.testActuator.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.Actuator.testActuator.services.HelloWorldService;
import com.test.Actuator.testActuator.utils.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldServiceTest implements BaseTest{

	@Autowired
	private HelloWorldService helloWorldService;
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void testSayHello() {
		String sayHello = helloWorldService.sayHello("shai");
		Assert.assertEquals("Shalom shai", sayHello);
	}
}
