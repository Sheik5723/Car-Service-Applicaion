package com.practice.csa.utility;

public class ErrorStructure<T> {

	private int statusCode;
	private String errorMessage;
	private T data;
	
	public int getStatusCode() {
		return statusCode;
	}
	public ErrorStructure<T> setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public ErrorStructure<T> setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
	public T getData() {
		return data;
	}
	public ErrorStructure<T> setData(T data) {
		this.data = data;
		return this;
	}
	
	
}
