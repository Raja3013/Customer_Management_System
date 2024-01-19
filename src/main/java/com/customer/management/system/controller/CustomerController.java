package com.customer.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
		// Process customer details before saving to the database
		CustomerDto processedCustomer = customerService.processCustomerDetails(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(processedCustomer);

        }catch (IllegalArgumentException e) {
            // Handle specific validation exceptions
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            // Handle other unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
   
	}
}
