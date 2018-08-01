package com.example.jena.jenaDemo;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean
	public Directory getRAMDirectory() {
		return new RAMDirectory();
	}
}
