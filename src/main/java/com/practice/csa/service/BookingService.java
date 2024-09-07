package com.practice.csa.service;

import org.springframework.http.ResponseEntity;

import com.practice.csa.responsedto.BookingResponseDto;
import com.practice.csa.utility.ResponseStructure;

public interface BookingService {

	ResponseEntity<ResponseStructure<BookingResponseDto>> createBookinng(int carId);

}
