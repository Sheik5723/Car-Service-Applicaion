package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.requestdto.AuthRequest;
import com.practice.csa.requestdto.UserRequestDto;
import com.practice.csa.responsedto.UserResponseDto;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/user-login")
	public ResponseEntity<ResponseStructure<String>> login(@RequestBody AuthRequest authRequest) {
		return service.login(authRequest);
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerUser(@RequestBody UserRequestDto request){
		return service.registerUser(request);
	}
	
	@GetMapping("/users/{userId}")
	private ResponseEntity<ResponseStructure<UserResponseDto>> findUserById(@PathVariable int userId){
		return service.findUseById(userId);
	}
	
	@PutMapping("/users/{userId}")
	private ResponseEntity<ResponseStructure<UserResponseDto>> updateUserById(@RequestBody UserRequestDto request){
		return service.updateUser(request);
	}
	
	@DeleteMapping("/users/{userId}")
	private ResponseEntity<ResponseStructure<UserResponseDto>> deleteUserById(@PathVariable int userId){
		return service.deleteUserById(userId);
	}
	
	@GetMapping("/users")
	private ResponseEntity<ResponseStructure<List<UserResponseDto>>> findAllUsers(){
		return service.findAllUsers();
	}
}
