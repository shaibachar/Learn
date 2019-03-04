package com.cache.HelloSpringCache;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cache.HelloSpringCache.configuration.ApplicationProperties;
import com.cache.HelloSpringCache.model.Client;
import com.cache.HelloSpringCache.repository.ClientRepository;
import com.cache.HelloSpringCache.service.CachingService;

@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class StartupRunner implements CommandLineRunner {

	@Value("${application.myPort}")
	private String myPort;
	
	private boolean init = false;
	private final ClientRepository clientRepository;
	private final CachingService cachingService;
	private ApplicationProperties applicationProperties;
	
	public StartupRunner(ClientRepository clientRepository,CachingService cachingService,ApplicationProperties applicationProperties) {
		this.clientRepository = clientRepository;
		this.cachingService = cachingService;
		this.applicationProperties = applicationProperties;
		
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(MessageFormat.format("Starting env:{0}", applicationProperties.getEnvName()));
		System.out.println(MessageFormat.format("Starting port:{0}", myPort));
		init = true;
		Map<String, Client> data = getData();
		clientRepository.loadData(data);
	}

	private Map<String, Client> getData() {
		Map<String, Client> res = new HashMap<>();
		res.put("111", new Client("111","aaa","bbb","1111"));
		res.put("112", new Client("112","aaa1","bbb","1111"));
		res.put("113", new Client("113","aaa2","bbb","1111"));
		res.put("114", new Client("114","aaa3","bbb","1111"));
		res.put("115", new Client("115","aaa4","bbb","1111"));
		res.put("116", new Client("116","aaa5","bbb","1111"));
		res.put("117", new Client("117","aaa6","bbb","1111"));
		res.put("118", new Client("118","aaa7","bbb","1111"));
		res.put("119", new Client("119","aaa8","bbb","1111"));
		
		return res;
	}

	@Scheduled(fixedDelay = 60000)
	public void updateCache() {
		if (init) {
			System.out.println("Going to clean client cache");
			cachingService.evictAllCaches();
		}
	}


}