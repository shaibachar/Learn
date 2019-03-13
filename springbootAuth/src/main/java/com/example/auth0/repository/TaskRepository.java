package com.example.auth0.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth0.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}