package com.interview.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	public void addCorsMapping(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/api/**").allowedOrigins("http://localhost:4000")
				.allowedMethods("POST", "GET", "PUT", "PATCH", "DELETE")
				.allowedHeaders("Origin", "Accept", "Authorization", "Content-Type").allowCredentials(true)
				.maxAge(3600);
	}

}
