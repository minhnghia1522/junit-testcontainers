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

//	@Value("${app.clean-database-after-test}")
//	public Boolean isClean;
//
//	@Autowired
//	public JdbcTemplate jdbcTemplate;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", conatiner::getJdbcUrl);
		registry.add("spring.datasource.username", conatiner::getUsername);
		registry.add("spring.datasource.password", conatiner::getPassword);
	}

//	protected void resetDatabase() {
//		if (isClean) {
//			jdbcTemplate.queryForList(
//					"select 'truncate table \"' || tablename || '\" cascade;' from pg_tables where schemaname='public';\n",
//					String.class).forEach(jdbcTemplate::update);
//			jdbcTemplate.queryForList(
//					"select 'drop sequence if exists \"' || relname || '\" cascade;'from pg_class where relkind='S';\n",
//					String.class).forEach(jdbcTemplate::update);
//		}
//
//	}
}
