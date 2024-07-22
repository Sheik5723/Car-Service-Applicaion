package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.requestdto.CarRequestDto;
import com.practice.csa.responsedto.CarResponseDto;
import com.practice.csa.utility.ResponseStructure;

public interface CarService {

	public ResponseEntity<ResponseStructure<CarResponseDto>> addCar(CarRequestDto car);

	public ResponseEntity<ResponseStructure<CarResponseDto>> updateCarById(int carId, CarRequestDto updatedCar);

	public ResponseEntity<ResponseStructure<CarResponseDto>> findCarById(int carId);

	public ResponseEntity<ResponseStructure<CarResponseDto>> deleteCar(int carId);

	public ResponseEntity<ResponseStructure<List<CarResponseDto>>> findAllCars();

	
}
