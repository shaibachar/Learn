package com.example.auth0.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth0.domain.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
	ApplicationUser findByUsername(String username);
}
