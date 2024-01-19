package com.example.customermanagementsystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.customer.management.system.Dto.CustomerDto;
import com.customer.management.system.entities.CustomerEntity;
import com.customer.management.system.repository.CustomerRepository;
import com.customer.management.system.service.CustomerServiceImpl;

public class CustomerEntityServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    // Initialize mocks
    public CustomerEntityServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessCustomerDetails_Success() {
        // Creating a sample customerDto
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("John Doe");
        customerDto.setEmail("john@example.com");
        customerDto.setDob("23-09-1992");
        customerDto.setOccupation("developer");

        // Mocking the save operation to return the same customerEntity
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerService.customerDtoToCustomerEntity(customerDto));

        // Performing the test
        CustomerDto result = customerService.processCustomerDetails(customerDto);

        // Asserting the result
        assertNotNull(result);
        assertEquals(customerDto.getName(), result.getName());
        assertEquals(customerDto.getEmail(), result.getEmail());
        assertEquals(customerDto.getDob(), result.getDob());
        assertEquals(customerDto.getOccupation(), result.getOccupation());
    }

}
