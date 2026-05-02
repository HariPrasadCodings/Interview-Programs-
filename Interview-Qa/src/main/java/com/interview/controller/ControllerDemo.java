package com.interview.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerDemo {

	// URL: http://localhost:2025/controller/message
	@GetMapping("/controller/message")
	private String printMessage() {
		return "hello";
	}

	// URL: http://localhost:2025/greetingMessage
	@GetMapping(value = "/greetingMessage", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String message() {
		return "Welcome to Hyderabad Mr: HariPrasad";
	}

}
