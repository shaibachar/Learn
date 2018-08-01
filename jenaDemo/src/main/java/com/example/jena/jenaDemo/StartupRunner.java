package com.example.jena.jenaDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.jena.jenaDemo.services.IndexService;
import com.example.jena.jenaDemo.services.QueryService;

@Component
public class StartupRunner implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

	private final IndexService indexService;
	private final QueryService queryService;

	public StartupRunner(IndexService indexService, QueryService queryService) {
		super();
		this.indexService = indexService;
		this.queryService = queryService;
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
