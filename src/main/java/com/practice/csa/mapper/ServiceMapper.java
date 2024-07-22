package com.practice.csa.mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.entity.CarService;
import com.practice.csa.requestdto.ServiceRequestDto;
import com.practice.csa.responsedto.ServiceResponseDto;

@Component
public class ServiceMapper {

	public CarService mapToService(ServiceRequestDto request) {
		
		CarService service = new CarService();
		
		service.setServiceType(request.getServiceType());
		service.setServiceCost(request.getServiceCost());
		service.setDescription(request.getDescription());
		
		return service;
		
	}
	
	public ServiceResponseDto mapToServiceResponse(CarService service) {
		
		ServiceResponseDto response = new ServiceResponseDto();
		
		response.setServiceId(service.getServiceId());
		response.setServiceType(service.getServiceType());
		response.setServiceCost(service.getServiceCost());
		response.setDescription(service.getDescription());
		
		return response;
		
	}
}
