package com.cache.HelloSpringCache;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cache.HelloSpringCache.configuration.ApplicationProperties;
import com.cache.HelloSpringCache.model.CacheClients;
import com.cache.HelloSpringCache.model.Client;
import com.cache.HelloSpringCache.repository.ClientRepository;
import com.cache.HelloSpringCache.service.CachingService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class StartupRunner implements CommandLineRunner {

	private ObjectMapper mapper;
	private boolean init = false;
	private final ClientRepository clientRepository;
	private final CachingService cachingService;
	private final ApplicationProperties applicationProperties;

	public StartupRunner(ClientRepository clientRepository, CachingService cachingService,
			ApplicationProperties applicationProperties) {
		mapper = new ObjectMapper();
		this.clientRepository = clientRepository;
		this.cachingService = cachingService;
		this.applicationProperties = applicationProperties;
	}

	@Override
	public void run(String... args) throws Exception {
		init = true;
		Map<String, List<Client>>  data = getData(applicationProperties.useLocalCache());
		clientRepository.loadData(data);
	}

	private Map<String, List<Client>> getData(Boolean useLocalCache) {
		String cacheFilePath = applicationProperties.getCacheFilePath();
		File file = new File(cacheFilePath);
		Map<String, List<Client>> res = getClients();
		if (!useLocalCache) {
			file.delete();
			try {
				CacheClients cacheClients = new CacheClients();
				cacheClients.setClientList(res);
				mapper.writeValue(file, cacheClients);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (file.exists()) {
				try {
					TypeReference<CacheClients> typeReference = new TypeReference<CacheClients>() {
					};
					CacheClients readValue = mapper.readValue(file, typeReference);
					Map<String, List<Client>> clientList = readValue.getClientList();
					res = clientList;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	private Map<String, List<Client>> getClients() {
		Map<String, List<Client>> res = new HashMap<>();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 100000; i++) {
			List<Client> clientList = new ArrayList<>();
			for (int j = 0; j < 10; j++) {
				Client client = new Client("111" + i, "aaa" + i, "bbb", "1111");
				clientList.add(client);
			}
			res.put("111" + i, clientList);
		}
		return res;
	}

	@Scheduled(fixedDelay = 100000)
	public void updateCache() {
		if (init) {
			System.out.println("updateCache");
			Map<String, List<Client>> data = getData(applicationProperties.useLocalCache());
			clientRepository.loadData(data);
			cachingService.evictAllCaches();
		}
	}

}