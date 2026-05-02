package com.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ErrorDetails handleProductNotFoundException(ProductNotFoundException exception) {
		return ErrorDetails.builder().status("FAILED").message(exception.getMessage()).statusCode(HttpStatus.NOT_FOUND)
				.build();
	}

	@ExceptionHandler(DuplicateProductFoundException.class)
	public ErrorDetails handleDuplicateProductFoundException(DuplicateProductFoundException exception) {
		return ErrorDetails.builder().status("FAILED").message(exception.getMessage()).statusCode(HttpStatus.NOT_FOUND)
				.build();
	}

}
