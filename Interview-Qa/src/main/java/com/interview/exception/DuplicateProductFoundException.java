package com.interview.exception;

public class DuplicateProductFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateProductFoundException(String message) {
		super(message);
	}

}
