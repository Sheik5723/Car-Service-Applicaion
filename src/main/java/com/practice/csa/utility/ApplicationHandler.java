package com.practice.csa.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.practice.csa.exception.CarNotFoundException;
import com.practice.csa.exception.ServiceNotFoundException;

@RestControllerAdvice
public class ApplicationHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> carNotFoundException(CarNotFoundException ex){
		
		ErrorStructure<String> errorStructure = new ErrorStructure<String>();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setErrorMessage(ex.getMessage());
		errorStructure.setData("Car Object not Found");
		
		return new ResponseEntity<ErrorStructure<String>>(errorStructure , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> serviceNotFoundException(ServiceNotFoundException ex){
		
		ErrorStructure<String> errorStructure = new ErrorStructure<String>();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setErrorMessage(ex.getMessage());
		errorStructure.setData("Service Object not Found");
		
		return new ResponseEntity<ErrorStructure<String>>(errorStructure , HttpStatus.NOT_FOUND);
	}
}
