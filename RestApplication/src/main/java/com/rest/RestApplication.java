package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rest.model.DataSourceConfig;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {

	@Value("${name}")
	private String name;

	@Autowired
	private DataSourceConfig config;

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Name : " + name);
		System.out.println(config);

	}

}
