package com.example.auth0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class StartupRunner implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
