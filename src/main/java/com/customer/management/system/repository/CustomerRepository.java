/**
 * 
 */
/**
 * @author User
 *
 */
package com.customer.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.management.system.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
//	CustomerEntity findByEmail(String email);
//
//	Object findByOccupationAndDobAndCustomerGroup(String occupation, String string, String customerGroup);
//	// findByOccupationAndDobAndCustomerGroup
}
