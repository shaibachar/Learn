package com.hello.HelloRestAssured;


import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.hello.HelloRestAssured.domain.Customer;
import com.hello.HelloRestAssured.domain.Product;
import com.hello.HelloRestAssured.service.CustomerService;
import com.hello.HelloRestAssured.service.HelloService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureHttpClient;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import wiremock.com.fasterxml.jackson.core.JsonProcessingException;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest("app.baseUrl=https://localhost:6443")
@AutoConfigureHttpClient
public class WiremockForDocsTests {
    @ClassRule
    public static WireMockClassRule wiremock = new WireMockClassRule(
            WireMockSpring.options().httpsPort(6443));

    @Autowired
    private CustomerService service;

    private List<Customer> customers;

    @Before
    public void init() {
        customers = new ArrayList<Customer>();
        Product product1 = new Product("1", "a1", "desc1", 100d);
        Product product2 = new Product("2", "a2", "desc2", 100d);
        Product product3 = new Product("3", "a3", "desc3", 100d);
        Product product4 = new Product("4", "a4", "desc4", 100d);
        Customer customer1 = new Customer("1", "shai1", 100);
        Customer customer2 = new Customer("2", "shai2", 100);
        customer1.addProductList(product1);
        customer1.addProductList(product2);
        customer2.addProductList(product1);
        customer2.addProductList(product3);
        customer2.addProductList(product4);
        customers.add(customer1);
        customers.add(customer2);
    }

    @Test
    public void contextLoads() throws Exception {
        String customerList = getCustomersAsJson();

        stubFor(get(urlEqualTo("/resource")).willReturn(aResponse()
                .withHeader("Content-Type", "text/plain").withBody(customerList)));
        List<Customer> allCustomers = this.service.getAllCustomersFrom();
        assertThat(allCustomers).isEqualTo(customerList);
    }

    public String getCustomersAsJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(customers);
    }

}