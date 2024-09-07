package com.practice.csa.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.csa.entity.Booking;
import com.practice.csa.entity.CarService;
import com.practice.csa.responsedto.BookingResponseDto;
import com.practice.csa.responsedto.ServiceResponseDto;

@Component
public class BookingMapper {
	
	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private ServiceMapper serviceMapper;

	
	public BookingResponseDto mapToBookingResponseDto(Booking booking) {
		
		BookingResponseDto bookingResponse = new BookingResponseDto();
		bookingResponse.setBookingId(booking.getBookingId());
		bookingResponse.setCar(carMapper.mapToCarResonse(booking.getCar()));
		
		List<ServiceResponseDto> response = booking.getContracts().stream().map(contract->{
			
			CarService carService = contract.getCarService();
			
			return serviceMapper.mapToServiceResponse(carService);
		}).toList();
		
		bookingResponse.setServices(response);
		
		return bookingResponse;
	}
}
