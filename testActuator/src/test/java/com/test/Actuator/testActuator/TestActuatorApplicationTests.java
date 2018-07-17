package com.test.Actuator.testActuator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.Actuator.testActuator.services.HelloWorldService;
import com.test.Actuator.testActuator.utils.BaseTest;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestActuatorApplicationTests implements BaseTest{

	@Autowired
	private HelloWorldService helloWorldService;
	
	
	@Test
	public void sayHelloWrold() {
		String sayHello = helloWorldService.sayHello("shai");
		Assert.assertEquals("","Shalom shai", sayHello);
	}

	
	@Test
	public void contextLoads() {
	}

}
