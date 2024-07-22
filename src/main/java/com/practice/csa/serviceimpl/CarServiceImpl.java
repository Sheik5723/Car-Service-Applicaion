package com.practice.csa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Car;
import com.practice.csa.exception.CarNotFoundException;
import com.practice.csa.mapper.CarMapper;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.requestdto.CarRequestDto;
import com.practice.csa.responsedto.CarResponseDto;
import com.practice.csa.service.CarService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CarMapper carMapper;

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> addCar(CarRequestDto request) {
		
		Car car = carMapper.mapToCar(request);

		carRepository.save(car);
		
		CarResponseDto response = carMapper.mapToCarResonse(car);
		
		ResponseStructure<CarResponseDto> rs = new ResponseStructure<CarResponseDto>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Car object inserted Successfully");
		rs.setData(response);
		
		return new ResponseEntity<ResponseStructure<CarResponseDto>>(rs , HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> updateCarById(int carId, CarRequestDto request) {
	
		return carRepository.findById(carId)
			.map(car->{
				
				Car updatedCar = carMapper.mapToCar(request);
				updatedCar.setCarId(carId);
				
				return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<CarResponseDto>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("Updated Successfully")
							.setData(carMapper.mapToCarResonse(carRepository.save(updatedCar)))
							);
			})
			.orElseThrow(()->new CarNotFoundException("Failed To Found"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> findCarById(int carId) {
		
		return carRepository.findById(carId)
				.map(car->
					 ResponseEntity.status(HttpStatus.FOUND)
					 .body(new ResponseStructure<CarResponseDto>()
							.setStatusCode(HttpStatus.FOUND.value())
							.setMessage("Car Id "+carId+" is Found")
							.setData(carMapper.mapToCarResonse(car))))
				.orElseThrow(()->new CarNotFoundException("Failed To Find Car"));
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> deleteCar(int carId) {

		return carRepository.findById(carId)
			.map(car->{
				carRepository.delete(car);
				return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<CarResponseDto>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("Deleted Successfully")
							.setData(carMapper.mapToCarResonse(car))
							);
			})
			.orElseThrow(()->new CarNotFoundException("Failed To Found"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CarResponseDto>>> findAllCars() {

			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<List<CarResponseDto>>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("Deleted Successfully")
							.setData(carRepository.findAll()
									.stream()
									.map(car->carMapper.mapToCarResonse(car))
									.toList())
							);
	}

}
