package com.json.example.jsonExample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.json.example.jsonExample.domain.User;
import com.json.example.jsonExample.services.UsersService;

@RestController
@RequestMapping("/users")
public class MyController {

	private final UsersService usersService;

	public MyController(UsersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping("/allClients")
	public List<User> allClients() {
		List<User> res = new ArrayList<>();
		Map<String, User> data = usersService.getData();
		List<User> values = data.values().stream().collect(Collectors.toList());
		return values == null || values.isEmpty() ? res : values;
	}
}
