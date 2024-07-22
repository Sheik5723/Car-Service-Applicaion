package com.practice.csa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.CarService;
import com.practice.csa.exception.ServiceNotFoundException;
import com.practice.csa.mapper.ServiceMapper;
import com.practice.csa.repository.ServiceRepository;
import com.practice.csa.requestdto.ServiceRequestDto;
import com.practice.csa.responsedto.ServiceResponseDto;
import com.practice.csa.service.ServiceService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceRepository repository;

	@Autowired
	private ServiceMapper mapper;

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> addService(ServiceRequestDto request) {

		CarService service = mapper.mapToService(request);

		service = repository.save(service);

		ServiceResponseDto response = mapper.mapToServiceResponse(service);

		ResponseStructure<ServiceResponseDto> rs = new ResponseStructure<ServiceResponseDto>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Service Added Successfully");
		rs.setData(response);

		return new ResponseEntity<ResponseStructure<ServiceResponseDto>>(rs, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> findServiceById(int serviceId) {

		return repository.findById(serviceId)
			.map(service->ResponseEntity
					.status(HttpStatus.FOUND)
					.body(new ResponseStructure<ServiceResponseDto>()
							.setStatusCode(HttpStatus.FOUND.value())
							.setMessage("Service Found")
							.setData(mapper.mapToServiceResponse(service))
						 )
				)
			.orElseThrow(()->new ServiceNotFoundException("Service Not Found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> updateServiceById(int serviceId,ServiceRequestDto request) {
		
		
		return repository.findById(serviceId)
			.map(service->{
				
				CarService updatedService = mapper.mapToService(request);
				updatedService.setServiceId(serviceId);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(new ResponseStructure<ServiceResponseDto>()
								.setStatusCode(HttpStatus.OK.value())
								.setMessage("Updated Successfully")
								.setData(mapper.mapToServiceResponse(repository.save(updatedService)))
								);
			})
			.orElseThrow(()->new ServiceNotFoundException("Service Not Found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> deleteServiceById(int serviceId) {
		
		return repository.findById(serviceId)
			.map(service->{ 
				repository.delete(service);
				return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<ServiceResponseDto>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("Deleted Successfully")
							.setData(mapper.mapToServiceResponse(service))
							);
			})
			.orElseThrow(()->new ServiceNotFoundException("Service not found"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> findAllServices() {

			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseStructure<List<ServiceResponseDto>>()
							.setStatusCode(HttpStatus.OK.value())
							.setMessage("Found Successfully")
							.setData(repository.findAll()
									.stream()
									.map(service->mapper.mapToServiceResponse(service))
									.toList())
							);
	}

}
