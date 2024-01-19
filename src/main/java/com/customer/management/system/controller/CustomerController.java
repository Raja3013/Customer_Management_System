package com.customer.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.management.system.Dto.CustomerDto;
import com.customer.management.system.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
		
		// Process customer details before saving to the database
		CustomerDto processedCustomer = customerService.processCustomerDetails(customerDto);
		// Return the saved customer
		return ResponseEntity.status(HttpStatus.CREATED).body(processedCustomer);
	}
}
