package com.igu.developer.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void simple() {
		//{ "query": "{client(id: 1) {id,name,isChild} health(id: 2) {name,score,isGood}}"}
	}
}
