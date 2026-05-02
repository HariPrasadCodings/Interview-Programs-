package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecuriyConfig {

//	@Bean
//	UserDetailsService userDetailsService() {
//		UserDetails userDetails = User.builder().username("user")
//				.password("$2a$12$Lu8F.2WmQxb/ps7jMbBWuOW01GStejbs1mgrFLN0UXQbAltNMphWe").build();
//		return new InMemoryUserDetailsManager(userDetails);
//	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
