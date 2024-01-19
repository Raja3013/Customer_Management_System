package com.example.customermanagementsystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.customer.management.system.Dto.CustomerDto;
import com.customer.management.system.controller.CustomerController;
import com.customer.management.system.service.CustomerService;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    // Initialize mocks
    public CustomerControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCustomer_Success() {
        CustomerDto requestDto = new CustomerDto("John Doe", "john@example.com", "22-07-1992", "developer","NA");
        CustomerDto processedCustomer = new CustomerDto();
        processedCustomer.setName(requestDto.getName());
        processedCustomer.setEmail(requestDto.getEmail());
        processedCustomer.setDob(requestDto.getDob());
        processedCustomer.setOccupation(requestDto.getOccupation());
        processedCustomer.setCustomerGroup("developer");

        when(customerService.processCustomerDetails(any(CustomerDto.class))).thenReturn(processedCustomer);

        ResponseEntity<CustomerDto> responseEntity = customerController.createCustomer(requestDto);

        verify(customerService, times(1)).processCustomerDetails(any(CustomerDto.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(processedCustomer, responseEntity.getBody());
    }

}
