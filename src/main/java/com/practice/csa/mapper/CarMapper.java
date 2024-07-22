package com.practice.csa.mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.entity.Car;
import com.practice.csa.requestdto.CarRequestDto;
import com.practice.csa.responsedto.CarResponseDto;

@Component
public class CarMapper {

	/**
	 * In the below method the request is coming in form of Request
	 */
	public Car mapToCar(CarRequestDto request) {
		
		Car car = new Car();
		
		car.setModel(request.getModel());
		car.setBrand(request.getBrand());
		
		return car;
	}
	
	public CarResponseDto mapToCarResonse(Car car) {
		
		CarResponseDto response = new CarResponseDto();
		
		response.setCarId(car.getCarId());
		response.setModel(car.getModel());
		response.setBrand(car.getBrand());
		
		return response;
	}
}
