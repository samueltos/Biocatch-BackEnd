package com.example.lbank.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}



	public List<CustomerDetails> getCustomerInfo(){
		return customerRepository.findAll();
	}



	public void addNewCustomer(CustomerDetails customerDetails) {
		// can perform some validation here later
		customerRepository.save(customerDetails);		
	}
}
