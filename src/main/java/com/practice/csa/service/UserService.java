package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.requestdto.AuthRequest;
import com.practice.csa.requestdto.UserRequestDto;
import com.practice.csa.responsedto.UserResponseDto;
import com.practice.csa.utility.ResponseStructure;

public interface UserService {
	
	ResponseEntity<ResponseStructure<String>> login(AuthRequest authRequest);

	ResponseEntity<ResponseStructure<UserResponseDto>> registerUser(UserRequestDto request);

	ResponseEntity<ResponseStructure<UserResponseDto>> findUseById(int userId);

	ResponseEntity<ResponseStructure<UserResponseDto>> updateUser(UserRequestDto request);

	ResponseEntity<ResponseStructure<UserResponseDto>> deleteUserById(int userId);

	ResponseEntity<ResponseStructure<List<UserResponseDto>>> findAllUsers();

}
