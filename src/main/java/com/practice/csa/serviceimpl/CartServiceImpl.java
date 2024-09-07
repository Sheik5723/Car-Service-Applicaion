package com.practice.csa.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.CarService;
import com.practice.csa.entity.Cart;
import com.practice.csa.exception.ServiceNotFoundException;
import com.practice.csa.exception.UserNotFoundException;
import com.practice.csa.mapper.ServiceMapper;
import com.practice.csa.repository.CartRepository;
import com.practice.csa.repository.ServiceRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.responsedto.CartResponseDto;
import com.practice.csa.responsedto.ServiceResponseDto;
import com.practice.csa.service.CartService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceMapper serviceMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<ResponseStructure<CartResponseDto>> addServiceToCart(int serviceId) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepository.findByEmail(email)
		.map(user->{
			Cart cart = user.getCart();
			if(cart.getCarServices() == null)
				cart.setCarServices(new ArrayList<CarService>());
			return  serviceRepository.findById(serviceId)
			.map(service->{
				cart.getCarServices().add(service);
				Cart updatedCart = cartRepository.save(cart);
				CartResponseDto cartResponse = new CartResponseDto();
				cartResponse.setCartId(updatedCart.getCartId());
				List<ServiceResponseDto> services = updatedCart.getCarServices().stream()
													.map(serviceMapper::mapToServiceResponse)
													.toList();
				cartResponse.setServices(services);
				
				return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(	new ResponseStructure<CartResponseDto>()
						.setStatusCode(HttpStatus.CREATED.value())
						.setMessage("Cart Updated")
						.setData(cartResponse)
						);
				
			}).orElseThrow(()->new ServiceNotFoundException("Service Not Found"));
		}).orElseThrow(()->new UserNotFoundException("User Not Found"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<CartResponseDto>> removeServiceFromCart(int serviceId) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepository.findByEmail(email)
				.map(user->{
					Cart cart = user.getCart();
					
					return  serviceRepository.findById(serviceId)
					.map(service->{
						cart.getCarServices().remove(service);
						Cart updatedCart = cartRepository.save(cart);
						CartResponseDto cartResponse = new CartResponseDto();
						cartResponse.setCartId(updatedCart.getCartId());
						List<ServiceResponseDto> services = updatedCart.getCarServices().stream()
															.map(serviceMapper::mapToServiceResponse)
															.toList();
						cartResponse.setServices(services);
						
						return ResponseEntity
						.status(HttpStatus.OK)
						.body(	new ResponseStructure<CartResponseDto>()
								.setStatusCode(HttpStatus.OK.value())
								.setMessage("Service Deleted Cart Updated")
								.setData(cartResponse)
								);
						
					}).orElseThrow(()->new ServiceNotFoundException("Service Not Found"));
				}).orElseThrow(()->new UserNotFoundException("User Not Found"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<CartResponseDto>> findAllServiceFromCart() {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepository.findByEmail(email)
		.map(user->{
			
			Cart cart = user.getCart();
			
			List<ServiceResponseDto> services = cart.getCarServices().stream()
											.map(serviceMapper::mapToServiceResponse)
											.toList();
			
			CartResponseDto cartResponse = new CartResponseDto();
			cartResponse.setCartId(cart.getCartId());
			cartResponse.setServices(services);
			
			return ResponseEntity
			.status(HttpStatus.FOUND)
			.body(
					new ResponseStructure<CartResponseDto>()
					.setStatusCode(HttpStatus.FOUND.value())
					.setMessage("All Services  Found")
					.setData(cartResponse)
					);
			
		}).orElseThrow(()->new UserNotFoundException("User Not Found"));
		
		
		
	}
	
	
	
	//remove service from cart
	
	//find all services in particular cart
	
}
