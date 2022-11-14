package com.sysexevn.sunshinecity.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresqlContainer extends PostgreSQLContainer<PostgresqlContainer> {
	private static final String IMAGE_VERSION = "postgres:15.1";
	private static PostgresqlContainer container;

	private PostgresqlContainer() {
		super(IMAGE_VERSION);
	}

	public static PostgresqlContainer getInstance() {
		if (container == null) {
			container = new PostgresqlContainer();
		}
		return container;
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void stop() {
	}
}