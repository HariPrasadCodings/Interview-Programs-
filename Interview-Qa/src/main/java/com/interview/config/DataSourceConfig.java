package com.interview.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataSourceConfig {

	private String username;
	private String password;

}
