package com.cache.HelloSpringCache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cache.HelloSpringCache.model.Client;
import com.cache.HelloSpringCache.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class StartupRunner implements CommandLineRunner {

	private boolean init = false;
	private final ClientRepository clientRepository;
	
	public StartupRunner(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public void run(String... args) throws Exception {
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

	@Scheduled(fixedDelay = 260000)
	public void updateCache() {
		if (init) {
			log.debug("Going to clean client cache");
		}
	}


}