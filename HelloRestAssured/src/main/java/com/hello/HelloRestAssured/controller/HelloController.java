package com.hello.HelloRestAssured.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.HelloRestAssured.domain.Customer;
import com.hello.HelloRestAssured.service.HelloService;

@Component
@RestController
public class HelloController {

	private final HelloService helloService;

	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "hello";
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomers = helloService.getAllCustomers();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<List<Customer>>(allCustomers,httpHeaders,HttpStatus.OK);
	}

}
