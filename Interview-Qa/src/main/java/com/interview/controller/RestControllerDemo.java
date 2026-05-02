package com.interview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.entity.Book;

@RestController
public class RestControllerDemo {

	// URL: http://localhost:2025/restController/message
	@GetMapping("/restController/message")
	private String printMessage() {
		return "hello";
	}

	// URL: http://localhost:2025/books
	@PostMapping("/books")
	public String processBook(@RequestBody Book book) {
		return book.getTitle() + " New Book has been published on year  " + book.getPublicationYear();

	}

}
