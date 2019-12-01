package com.hello.HelloRestAssured;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;

import java.util.ArrayList;
import java.util.List;

import com.hello.HelloRestAssured.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.hello.HelloRestAssured.domain.Customer;
import com.hello.HelloRestAssured.domain.Product;

import io.restassured.RestAssured;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestAssuredApplicationTests extends AbstractTestNGSpringContextTests {

    // https://github.com/rest-assured/rest-assured/wiki/Usage

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerService customerService;

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void validateCustomersSchema() {
        // Given
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
                .freeze();

        // When
        get("/getAllCustomers").then().assertThat()
                .body(matchesJsonSchemaInClasspath("customer-schema.json").using(jsonSchemaFactory));
    }

    @Test
    public void testHome() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/helloWorld", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("Hello World");
    }

    @Test
    public void whenRequestGet_thenOK() {
        given().when().get("/helloWorld").then().statusCode(200);
    }
/*
    private List<Customer> loadData() {
        List<Customer> customers = new ArrayList<>();
        Product product1 = new Product("1", "a1", "desc1", 100d);
        Product product2 = new Product("2", "a2", "desc2", 100d);
        Product product3 = new Product("3", "a3", "desc3", 100d);
        Product product4 = new Product("4", "a4", "desc4", 100d);
        Customer customer1 = new Customer("1", "shai1", 100);
        Customer customer2 = new Customer("2", "shai2", 100);
        Customer customer3 = new Customer("3", "shai3", 100);
        customer1.addProductList(product1);
        customer1.addProductList(product2);
        customer2.addProductList(product1);
        customer2.addProductList(product3);
        customer2.addProductList(product4);
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        return customers;
    }

    @Test
    public void testJsonResponse() {
        Mockito.when(customerService.getAllCustomers()).thenReturn(loadData());
        given().when().get("/getAllCustomers").then().body("name", hasItems("shai1", "shai2"));
    }
*/
}
