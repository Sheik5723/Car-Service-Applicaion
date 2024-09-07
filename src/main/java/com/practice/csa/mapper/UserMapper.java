package com.practice.csa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.practice.csa.entity.User;
import com.practice.csa.requestdto.UserRequestDto;
import com.practice.csa.responsedto.UserResponseDto;

@Component
public class UserMapper {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User mapToUser(UserRequestDto request) {
		
		User user = new User();
		
		user.setUserName(request.getUserName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setUserRole(request.getRole());
		
		return user;
	}
	
	public UserResponseDto mapToUserResponse(User user) {
		
		UserResponseDto response = new UserResponseDto();
		
		response.setUserId(user.getUserId());
		response.setUserName(user.getUserName());
		response.setEmail(user.getEmail());
		response.setRole(user.getUserRole());
		
		return response;
	}
}
