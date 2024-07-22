package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.requestdto.ServiceRequestDto;
import com.practice.csa.responsedto.ServiceResponseDto;
import com.practice.csa.utility.ResponseStructure;

public interface ServiceService {

	ResponseEntity<ResponseStructure<ServiceResponseDto>> addService(ServiceRequestDto request);

	ResponseEntity<ResponseStructure<ServiceResponseDto>> findServiceById(int serviceId);

	ResponseEntity<ResponseStructure<ServiceResponseDto>> updateServiceById(int serviceId, ServiceRequestDto request);

	ResponseEntity<ResponseStructure<ServiceResponseDto>> deleteServiceById(int serviceId);

	ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> findAllServices();

}
