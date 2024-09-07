package com.practice.csa.serviceimpl;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Cart;
import com.practice.csa.entity.User;
import com.practice.csa.enums.UserRole;
import com.practice.csa.exception.UserNotFoundException;
import com.practice.csa.mapper.UserMapper;
import com.practice.csa.repository.CartRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.requestdto.AuthRequest;
import com.practice.csa.requestdto.UserRequestDto;
import com.practice.csa.responsedto.UserResponseDto;
import com.practice.csa.security.JwtService;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	
	@Override
	public ResponseEntity<ResponseStructure<String>> login(AuthRequest authRequest) {
		
		String username = authRequest.getUsername();
		String password = authRequest.getPassword();
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, null);
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		if(authentication.isAuthenticated()) {
			SecurityContextHolder
			.getContext()
			.setAuthentication(authentication);
			
			return userRepository.findByEmail(username)
			.map(user->{
				String jwt = jwtService.createJwt(username, user.getUserRole().name(), Duration.ofDays(1));
				return ResponseEntity
				.status(HttpStatus.OK)
				.body(new ResponseStructure<String>()
						.setStatusCode(HttpStatus.OK.value())
						.setMessage("Successfully Logged")
						.setData(jwt)
						);
				
			})
			.orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		}else {
			throw new UsernameNotFoundException("User Credential Not Found");
		}
	}
	

	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> registerUser(UserRequestDto request) {
		
		User user = mapper.mapToUser(request);
		user = userRepository.save(user);
		
		if(user.getUserRole().equals(UserRole.USER)) {
			Cart cart = new Cart();
			cart.setUser(user);
			cart = cartRepository.save(cart);
		}
		
		
		return ResponseEntity
		.status(HttpStatus.CREATED)
		.body(new ResponseStructure<UserResponseDto>()
				.setStatusCode(HttpStatus.CREATED.value())
				.setMessage("Inserted Successfully")
				.setData(mapper.mapToUserResponse(user))
				);
		
	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> findUseById(int userId) {

		return userRepository.findById(userId)
		.map(user->
			ResponseEntity.status(HttpStatus.FOUND)
			.body(new ResponseStructure<UserResponseDto>()
					.setStatusCode(HttpStatus.FOUND.value())
					.setMessage("User Found")
					.setData(mapper.mapToUserResponse(user))
					
					)
				)
		.orElseThrow(()->new UserNotFoundException("User Not Found"));
	
	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> updateUser(UserRequestDto request) {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(userName)
		.map(user->{
			User updatedUser = mapper.mapToUser(request);
			user = userRepository.save(updatedUser);
			return ResponseEntity.status(HttpStatus.OK)
			.body(new ResponseStructure<UserResponseDto>() 
					.setStatusCode(HttpStatus.OK.value())
					.setMessage("User Updated")
					.setData(mapper.mapToUserResponse(user))
					);
		})
		.orElseThrow(()->new UserNotFoundException("User Not Found"));
		
	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponseDto>> deleteUserById(int userId) {
		
		return userRepository.findById(userId)
			.map(user->{
				userRepository.delete(user);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseStructure<UserResponseDto>()
								.setStatusCode(HttpStatus.OK.value())
								.setMessage("User Deleted")
								.setData(mapper.mapToUserResponse(user))
								);
			})
			.orElseThrow(()->new UserNotFoundException("User Not Found"));
	
	}


	@Override
	public ResponseEntity<ResponseStructure<List<UserResponseDto>>> findAllUsers() {
		
		UserRepository userRepository2 = userRepository;
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new ResponseStructure<List<UserResponseDto>>()
						.setStatusCode(HttpStatus.OK.value())
						.setMessage("Found Successfully")
						.setData(
								userRepository2.findAll()
								.stream()
								.map(user->mapper.mapToUserResponse(user))
								.toList()
								)
						);
		
	}


	
}
