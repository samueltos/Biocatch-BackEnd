package com.example.lbank.customer;

import com.example.lbank.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Query("from Customer c inner join fetch c.customer where c.custpmerId = :customerId")
	public Customer getCustomer(@Param("customerId") String customerId){
		Customer customer = customerRepository.findByCustomerUUID(customerId)
				.orElseThrow(() -> new UserNotFoundException("Customer with UUID "+ customerId + " not found"));
		return customer;
	}

	@Query("from Customer c inner join fetch c.customer where c.email = :email")
	public Customer login(@Param("email") String email){
		Optional<Customer> customer = customerRepository.findByEmail(email);
		if (customer.isEmpty()) {
			return null;
		}else{
			return customer.orElse(new Customer());
		}
	}

	public Customer pass(String password, Customer customer){

		if (customer.getPassword().equals(password)) {
			return customer;
		}else{
			return null;
		}
	}










	@Query("from Customer c inner join fetch c.customer where c.email = :#{#loginDTO.email}")
	public String loginCustomer(@Param("loginDTO") LoginDTO loginDTO){
		String email = loginDTO.getEmail();
		String password = loginDTO.getPassword();
		Optional<Customer> customer = customerRepository.findByEmail(email);
		if (customer.isEmpty()) {
			return "Customer with email "+ email + " not found";
		}

		String customerPassword = customer.orElse(new Customer()).getPassword();

		if (customerPassword.equals(password)){
			return "Login Successful";
		}
		return "Login credential does not match our record";
	}
	public List<Customer> customers(){
		return customerRepository.findAll();
	}

	public void addCustomer(Customer customer) {
		customer.setCustomerId(UUID.randomUUID().toString());
		customerRepository.save(customer);
	}
}
