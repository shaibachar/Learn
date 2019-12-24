package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.MyService;

@RestController
public class MyController {

	private final MyService myService;
	
	public MyController(MyService myService) {
		this.myService = myService;
	}
	
	@GetMapping("/hello")
	public String getHello() {
		String helloWorld = myService.helloWorld();
		return helloWorld;
	}
}
