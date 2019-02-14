package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Health;

@Repository
public interface HealthRepository extends JpaRepository<Health, Long> {
	Health findById(String id);

}