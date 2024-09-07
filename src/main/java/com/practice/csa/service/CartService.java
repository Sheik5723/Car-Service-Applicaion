package com.practice.csa.service;


import org.springframework.http.ResponseEntity;

import com.practice.csa.responsedto.CartResponseDto;
import com.practice.csa.utility.ResponseStructure;

public interface CartService {

	ResponseEntity<ResponseStructure<CartResponseDto>> addServiceToCart(int serviceId);

	ResponseEntity<ResponseStructure<CartResponseDto>> removeServiceFromCart(int serviceId);

	ResponseEntity<ResponseStructure<CartResponseDto>> findAllServiceFromCart();

}
