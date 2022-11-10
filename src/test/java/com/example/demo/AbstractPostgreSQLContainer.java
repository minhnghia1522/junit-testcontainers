package com.example.demo;

import java.time.Duration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class AbstractPostgreSQLContainer {
	@Container
	public static PostgreSQLContainer<?> conatiner = new PostgreSQLContainer<>("postgres").withUsername("systemexe")
			.withPassword("systemexe").withDatabaseName("systemexe").withReuse(true)
			.withStartupTimeout(Duration.ofMinutes(5));

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", conatiner::getJdbcUrl);
		registry.add("spring.datasource.username", conatiner::getUsername);
		registry.add("spring.datasource.password", conatiner::getPassword);
	}
}
