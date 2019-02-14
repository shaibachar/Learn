package com.example;

import java.text.MessageFormat;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.example.domain.Client;
import com.example.domain.Health;
import com.example.services.ClientService;
import com.example.services.HealthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class StartupRunner implements CommandLineRunner {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private HealthService healthService;

	@Override
	public void run(String... args) throws Exception {
		log.info("LoadingData!!! ");
		Stream.of("shai,10,10", "yossi,20,11", "shalom,17,12", "shosi,30,13", "alex,19,14", "bracha,4,15",
				"shani,21,16", "mitzi,50,17", "yaron,44,18").forEach(data -> {
					Health health = new Health();
					Client client = new Client();
					String[] split = data.split(",");
					client.setName(split[0]);
					client.setAge(Integer.parseInt(split[1]));
					log.info(MessageFormat.format("save:{0}", client));
					clientService.saveClient(client);
					
					health.setName(split[0]);
					health.setScore(Float.parseFloat(split[2]));
					healthService.saveHealth(health);
				});

	}

}