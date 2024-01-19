package com.customer.management.system.Dto;

public class CustomerDto {

	private String name;
	private String email;
	private String dob;
	private String occupation;
	private String customerGroup;

	public String getName() {
		return name;
	}

	public CustomerDto(String name, String email, String dob, String occupation, String customerGroup) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.occupation = occupation;
		this.customerGroup = customerGroup;
	}

	public String getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}

	public CustomerDto() {
		super();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

}
