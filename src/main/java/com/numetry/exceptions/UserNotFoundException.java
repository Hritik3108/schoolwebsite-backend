package com.numetry.exceptions;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super("Resource not Found");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
}
