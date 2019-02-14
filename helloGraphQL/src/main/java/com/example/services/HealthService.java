package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.domain.Health;
import com.example.repository.HealthRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;

@Service
public class HealthService {

	private final HealthRepository healthRepository;

	public HealthService(HealthRepository healthRepository) {
		this.healthRepository = healthRepository;
	}

	@GraphQLQuery(name = "healths")
	public List<Health> getHealth() {
		return healthRepository.findAll();
	}

	@GraphQLQuery(name = "health")
	public Optional<Health> getHealthById(@GraphQLArgument(name = "id") Long id) {
		return healthRepository.findById(id);
	}

	@GraphQLMutation(name = "saveHealth")
	public Health saveHealth(@GraphQLArgument(name = "health") Health health) {
		return healthRepository.save(health);
	}

	@GraphQLMutation(name = "deleteHealth")
	public void deleteHealth(@GraphQLArgument(name = "id") Long id) {
		healthRepository.deleteById(id);
	}

	@GraphQLQuery(name = "isGood")
	public boolean isCool(@GraphQLContext Health health) {
		return health.getScore() > 15 ;
	}


}
