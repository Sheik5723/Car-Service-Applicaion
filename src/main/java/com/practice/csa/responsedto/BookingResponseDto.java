package com.practice.csa.responsedto;

import java.util.List;

public class BookingResponseDto {

	private int bookingId;
	private CarResponseDto car;
	private List<ServiceResponseDto> services;
	
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public CarResponseDto getCar() {
		return car;
	}
	public void setCar(CarResponseDto car) {
		this.car = car;
	}
	public List<ServiceResponseDto> getServices() {
		return services;
	}
	public void setServices(List<ServiceResponseDto> services) {
		this.services = services;
	}
	
	
}
