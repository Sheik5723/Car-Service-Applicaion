package com.practice.csa.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.responsedto.CartResponseDto;
import com.practice.csa.service.CartService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	
	@PostMapping("/services/{serviceId}/carts")
	public ResponseEntity<ResponseStructure<CartResponseDto>> addServiceToCart(@PathVariable int serviceId){
		return cartService.addServiceToCart(serviceId);
	}
	
	@DeleteMapping("/services/{serviceId}/carts")
	public ResponseEntity<ResponseStructure<CartResponseDto>> removeServiceFromCart(@PathVariable int serviceId){
		return cartService.removeServiceFromCart(serviceId);
	}
	
	@GetMapping("/services/carts")
	public ResponseEntity<ResponseStructure<CartResponseDto>> findAllServiceFromCart(){
		return cartService.findAllServiceFromCart();
	}
}
