package com.example.lucene.luceneDemo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.lucene.luceneDemo.service.IndexWriterService;
import com.example.lucene.luceneDemo.service.SearchService;

@Component
public class StartupRunner implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

	private final IndexWriterService indexWriterService;
	private final SearchService searchService;

	public StartupRunner(IndexWriterService indexWriterService, SearchService searchService) {
		this.indexWriterService = indexWriterService;
		this.searchService = searchService;
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> query = searchService.Query("שלום שלום");
		log.info(query.size() + " results:");
		for (String string : query) {
			log.info(string);
		}

	}

}
