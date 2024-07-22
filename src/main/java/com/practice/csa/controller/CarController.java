package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.requestdto.CarRequestDto;
import com.practice.csa.responsedto.CarResponseDto;
import com.practice.csa.service.CarService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class CarController {

	@Autowired
	private CarService carService;
	
	@PostMapping("/cars")
	public ResponseEntity<ResponseStructure<CarResponseDto>> addCar(@RequestBody CarRequestDto car){
		return carService.addCar(car);
	}
	
	@PutMapping("/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponseDto>> updateCarById(@PathVariable int carId , @RequestBody CarRequestDto updatedCar){
		return carService.updateCarById(carId , updatedCar);
	}
	
	@GetMapping("/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponseDto>> findCarById(@PathVariable int carId){
		return carService.findCarById(carId);
	}
	
	@DeleteMapping("/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponseDto>> deleteCarById(@PathVariable int carId){
		return carService.deleteCar(carId);
	}
	
	@GetMapping("/cars")
	public ResponseEntity<ResponseStructure<List<CarResponseDto>>> findAllCar(){
		return carService.findAllCars();
	}
	
}
