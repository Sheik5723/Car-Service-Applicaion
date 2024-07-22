package com.practice.csa.exception;

public class CarNotFoundException extends RuntimeException{
	
	private String message;

	public CarNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
