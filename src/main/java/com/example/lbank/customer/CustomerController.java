package com.example.lbank.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/v1/customer") 
public class CustomerController {
	
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Customer>> customers(){
		List<Customer> customer = customerService.customers();
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping("/logn")
	public ResponseEntity<?> loginC(@RequestBody LoginDTO loginDTO){
		String email = loginDTO.getEmail();
		String password = loginDTO.getPassword();

		String response = "";
		Customer eChecker = customerService.login(email);
		if(eChecker == null){
			response = "Customer with email "+email+" not found";
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		Customer pChecker = customerService.pass(password, eChecker);
		if(pChecker == null){
			response = "Password does not match the credential";
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(eChecker, HttpStatus.OK);
	}


	@GetMapping("/log")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
		String response = customerService.loginCustomer(loginDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> customerInfo(@PathVariable String customerId){
		Customer customer = customerService.getCustomer(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> newCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
