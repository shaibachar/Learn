package com.hello.HelloRestAssured;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestAssuredApplicationTests {

	//https://github.com/rest-assured/rest-assured/wiki/Usage
	
	@LocalServerPort
	int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	public void validateCustomersSchema() {
		// Given
		JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();

		// When
		get("/getAllCustomers").then().assertThat().body(matchesJsonSchemaInClasspath("customer-schema.json").using(jsonSchemaFactory));
	}
	
	@Test
	public void whenRequestGet_thenOK() {
		given().when().get("/helloWorld").then().statusCode(200);
	}

	@Test
	public void testJsonResponse() {
		given().when().get("/getAllCustomers").then().body("name",hasItems("shai1", "shai2"));
	}

	

	
}
