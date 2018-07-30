package com.example.helm;

import java.text.MessageFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelmSpringBootDemoController {

	@GetMapping("/")
	public String home() {
		return "Hello World.";
	}

	@GetMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name) {
		return MessageFormat.format("Hello {0}", name);
	}
}
