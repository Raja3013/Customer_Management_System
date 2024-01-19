package com.customer.management.system.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.management.system.Dto.CustomerDto;
import com.customer.management.system.Enum.CustomerGroup;
import com.customer.management.system.Enum.Occupation;
import com.customer.management.system.entities.CustomerEntity;
import com.customer.management.system.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerDto processCustomerDetails(CustomerDto customerDto) {
		// Validate DOB
		if (validateDOB(customerDto.getDob())) {
			throw new IllegalArgumentException("Customer must be 18 years or older.");
		}

		// Assign customer group based on rules
		customerDto.setCustomerGroup(assignCustomerGroup(customerDto.getEmail(), customerDto.getOccupation()));

		// Check for email uniqueness
		if (customerRepository.findByEmail(customerDto.getEmail()) != null) {
			throw new IllegalArgumentException("Email already exists.");
		}

		// Check for uniqueness based on occupation, DOB, and customer group
		if (customerRepository.findByOccupationAndDobAndCustomerGroup(customerDto.getOccupation(), customerDto.getDob(),
				customerDto.getCustomerGroup()) != null) {
			throw new IllegalArgumentException("Duplicate entry based on occupation, DOB, and customer group.");
		}

		CustomerEntity customerEntity = customerDtoToCustomerEntity(customerDto);
		return CustomerEntitytoDto(customerRepository.save(customerEntity));
	}

	private boolean validateDOB(String dob) {
		// Implement logic to check if the customer is below 18
		// Return true if below 18, false otherwise
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
		Date customerDate = null;
		try {
			customerDate = dateFormat.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date currentDate = new Date();
		long ageInMillis = currentDate.getTime() - customerDate.getTime();
		int ageInYears = (int) (ageInMillis / (1000 * 60 * 60 * 24 * 365.25));
		return ageInYears < 18;
	}

	private String assignCustomerGroup(String email, String occupation) {
		// Implement logic to assign customer group based on email and occupation
		// Return 'hikeon', 'chef', 'developer', or 'NA'
		if (email.endsWith("@hikeon.tech")) {
			return CustomerGroup.HIKEON.toString();
		} else if (occupation.equalsIgnoreCase(Occupation.DEVELOPER.toString())
				|| occupation.equalsIgnoreCase(Occupation.CHEF.toString())) {
			return occupation.toLowerCase(); // Assuming 'developer' and 'chef' as group names
		} else {
			return CustomerGroup.NA.toString();
		}
	}

	private CustomerDto CustomerEntitytoDto(CustomerEntity customerEntity) {
		CustomerDto customer = new CustomerDto();
		customer.setName(customerEntity.getName());
		customer.setEmail(customerEntity.getEmail());
		customer.setDob(customerEntity.getDob());
		customer.setOccupation(customerEntity.getOccupation());
		return customer;
	}

	public CustomerEntity customerDtoToCustomerEntity(CustomerDto customerDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName(customerDto.getName());
		customerEntity.setEmail(customerDto.getEmail());
		customerEntity.setDob(customerDto.getDob());
		customerEntity.setOccupation(customerDto.getOccupation());
		return customerEntity;
	}

}
