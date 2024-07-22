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

import com.practice.csa.requestdto.ServiceRequestDto;
import com.practice.csa.responsedto.ServiceResponseDto;
import com.practice.csa.service.ServiceService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService;

	@PostMapping("/services")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> addService(@RequestBody ServiceRequestDto request){
		return serviceService.addService(request);
	}
	
	@GetMapping("/services/{serviceId}")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> findServiceById(@PathVariable int serviceId){
		return serviceService.findServiceById(serviceId);
	}
	
	@PutMapping("/services/{serviceId}")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> updateServiceById(@PathVariable int serviceId , @RequestBody ServiceRequestDto request){
		return serviceService.updateServiceById(serviceId , request);
	}
	
	@DeleteMapping("/services/{serviceId}")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> deleteServiceById(@PathVariable int serviceId){
		return serviceService.deleteServiceById(serviceId);
	}
	
	@GetMapping("/services")
	public ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> findAllServices(){
		return serviceService.findAllServices();
	}
}
