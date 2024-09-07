package com.practice.csa.responsedto;

import java.util.List;

public class CartResponseDto {

	private int cartId;
	private  List<ServiceResponseDto> services;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public List<ServiceResponseDto> getServices() {
		return services;
	}
	public void setServices(List<ServiceResponseDto> services) {
		this.services = services;
	}
	
	
}
