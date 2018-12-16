package com.hello.HelloRestAssured.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String id;
	private String name;
	private Integer age;
	private List<Product> productList;

	public Customer(String id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.productList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Customer [Id=" + id + ", name=" + name + ", age=" + age + ", productList=" + productList + "]";
	}

	public void addProductList(Product product1) {
		if (productList==null) {
			productList = new ArrayList<>();
		}
		productList.add(product1);
	}

}
