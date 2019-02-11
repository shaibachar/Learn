package com.json.example.jsonExample;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.example.jsonExample.domain.User;

@Component
public class StartupRunner implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

	@Override
	public void run(String... args) throws Exception {
		
	}
}