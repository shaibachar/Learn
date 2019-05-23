package com.hello.HelloRestAssured.controller;

import com.hello.HelloRestAssured.domain.Customer;
import com.hello.HelloRestAssured.service.CustomerService;
import com.hello.HelloRestAssured.service.HelloService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<List<Customer>>(allCustomers,httpHeaders,HttpStatus.OK);
	}

	@GetMapping("/getAllCustomersFrom")
	public ResponseEntity<List<Customer>> getAllCustomersFrom() {
		List<Customer> allCustomers = customerService.getAllCustomersFrom();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<List<Customer>>(allCustomers,httpHeaders,HttpStatus.OK);
	}

}
