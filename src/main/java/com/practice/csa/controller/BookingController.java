package com.practice.csa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.responsedto.BookingResponseDto;
import com.practice.csa.service.BookingService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/cars/{carId}/bookings")
	public ResponseEntity<ResponseStructure<BookingResponseDto>> createBooking(@PathVariable int carId){
		return bookingService.createBookinng(carId);
	}
}
