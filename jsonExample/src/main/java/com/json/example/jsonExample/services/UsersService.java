package com.json.example.jsonExample.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.example.jsonExample.domain.User;

@Service
public class UsersService {

	private static final Logger log = LoggerFactory.getLogger(UsersService.class);

	private Map<String, User> data;

	public UsersService() {

	}

	public Map<String, User> getData() {
		if (data == null) {
			data = load();
		}
		return data;
	}

	private Map<String, User> load() {
		Map<String, User> res = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
			};
			ClassPathResource classPathResource = new ClassPathResource("/data/User.json");
			InputStream inputStream;
			inputStream = classPathResource.getInputStream();
			List<User> users = mapper.readValue(inputStream, typeReference);
			for (User user : users) {
				res.put(user.getId(), user);
			}
		} catch (IOException e) {
			log.error("error while load", e);
		}
		return res;
	}

}
