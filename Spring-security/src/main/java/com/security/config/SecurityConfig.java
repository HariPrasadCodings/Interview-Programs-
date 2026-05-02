package com.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig implements SecurityFilterChain {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.permitAll().and().logout().permitAll();
		return security.build();

	}

	@Override
	public boolean matches(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Filter> getFilters() {
		// TODO Auto-generated method stub
		return null;
	}

}
