package com.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	// http://localhost:2020/hello

	@GetMapping("/hello")
	public String sayHello() {
		return "Hi Hari Prasad!..";
	}
}
