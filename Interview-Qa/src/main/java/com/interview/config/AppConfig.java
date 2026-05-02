package com.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.interview.common.User;

@Configuration
public class AppConfig {

	@Bean
	public DataSourceConfig config() {
		return new DataSourceConfig();
	}

	@Bean
	public User user() {
		User user = new User();
		user.setUsername("Hari");
		user.setPassword("Password1@");

		return user;
	}

}
