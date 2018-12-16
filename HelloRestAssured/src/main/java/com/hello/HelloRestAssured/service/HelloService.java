package com.hello.HelloRestAssured.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hello.HelloRestAssured.domain.Customer;
import com.hello.HelloRestAssured.domain.Product;

@Service
public class HelloService {

	private List<Customer> customers;

	public HelloService() {
		customers = new ArrayList<>();
		loadData();
	}

	private void loadData() {
		Product product1 = new Product("1","a1", "desc1", 100d);
		Product product2 = new Product("2","a2", "desc2", 100d);
		Product product3 = new Product("3","a3", "desc3", 100d);
		Product product4 = new Product("4","a4", "desc4", 100d);
		Customer customer1 = new Customer("1","shai1", 100);
		Customer customer2 = new Customer("2","shai2", 100);
		customer1.addProductList(product1);
		customer1.addProductList(product2);
		customer2.addProductList(product1);
		customer2.addProductList(product3);
		customer2.addProductList(product4);
		customers.add(customer1);
		customers.add(customer2);
	}

	public List<Customer> getAllCustomers() {
		return customers;
	}

}
