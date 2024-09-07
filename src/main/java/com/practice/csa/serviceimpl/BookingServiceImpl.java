package com.practice.csa.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Booking;
import com.practice.csa.entity.Car;
import com.practice.csa.entity.Cart;
import com.practice.csa.entity.Contract;
import com.practice.csa.entity.User;
import com.practice.csa.exception.CarNotFoundException;
import com.practice.csa.exception.UserNotFoundException;
import com.practice.csa.mapper.BookingMapper;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.repository.ContractRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.responsedto.BookingResponseDto;
import com.practice.csa.service.BookingRepository;
import com.practice.csa.service.BookingService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookingMapper bookingMapper;
	
	@Autowired
	private ContractRepository contractRepository;

	@Override
	public ResponseEntity<ResponseStructure<BookingResponseDto>> createBookinng(int carId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user =  userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User Not Found"));
		
		Booking booking = new Booking();
		
		Booking savedBooking = bookingRepository.save(booking);
		
		Car car = carRepository.findById(carId).orElseThrow(()->new CarNotFoundException("Car Not Found"));
		
		Cart cart = user.getCart();
		
		List<Contract> contracts = new ArrayList<Contract>(cart.getCarServices().stream()
															.map(service->{
																Contract contract = new Contract();
																contract.setCarService(service);
																contract.setBooking(savedBooking);
																return contractRepository.save(contract);
															}).toList());
		
		savedBooking.setCar(car);
		savedBooking.setContracts(contracts);
		savedBooking.setCustomer(user);
		
		Booking updatedBooking = bookingRepository.save(savedBooking);
		
		return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<BookingResponseDto>()
							.setStatusCode(HttpStatus.CREATED.value())
							.setMessage("Booking Created")
							.setData(bookingMapper.mapToBookingResponseDto(updatedBooking))
							);
		
	}

}
