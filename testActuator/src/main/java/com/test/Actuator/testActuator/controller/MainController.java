package com.test.Actuator.testActuator.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.Actuator.testActuator.services.HelloWorldService;

@RestController
public class MainController {

	@Autowired
	private HelloWorldService helloWorldService;

	@GetMapping("/hello/{name}")
	private String hello(@PathVariable("name") String name) {
		return helloWorldService.sayHello(name);
	}

	@GetMapping("/getMyFile/{value}")
	private String getMyFile(@PathVariable("value") String value) {
		String res = "";

		// res =
		// MainController.class.getResource("../../../../../application.properties").getPath();
		// File file = new File(res);
		// ClassPathResource classPathResource = new ClassPathResource(value);
		
		URLConnection openConnection;
		try {
			URL url1 = new URL(value);
			openConnection = url1.openConnection();
			URL url = openConnection.getURL();
			res = url.getPath();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// res = classPathResource.getURL().openConnection().getURL().toString();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//

		return res;

	}

}
