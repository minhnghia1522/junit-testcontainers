package com.sysexevn.sunshinecity.config;

import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
public class AbsTest {

	@ClassRule
	@Container
	public static PostgreSQLContainer<?> conatiner = PostgresqlContainer.getInstance();

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", conatiner::getJdbcUrl);
		registry.add("spring.datasource.username", conatiner::getUsername);
		registry.add("spring.datasource.password", conatiner::getPassword);
	}

//	@BeforeEach
//	void resetDatabase() {
////		h2Util.resetDatabase();
//		conatiner.
//	}
}
