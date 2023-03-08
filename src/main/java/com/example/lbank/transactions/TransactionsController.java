package com.example.lbank.transactions;

import com.example.lbank.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/v1/transactions")
public class TransactionsController {

	TransactionService transactionService;
	
	@Autowired
	public TransactionsController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping("/")
	public ResponseEntity<Set<Transactions>> getTransaction(@RequestBody Customer customer) {
		Set<Transactions> transactions = transactionService.getTransactions(customer);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
	
	@PostMapping("/transaction")
	public ResponseEntity<?> postTransaction(@RequestBody TransactionDTO transactionDTO) {
		Double price = transactionDTO.getAmount();
		String customerId = transactionDTO.getCustomerId();
		if(!transactionService.checkBalance(price,customerId)){
			return new ResponseEntity<>("You do not have enough balance", HttpStatus.INSUFFICIENT_STORAGE);
		}
		Transactions response = transactionService.addTransaction(transactionDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/deposit")
	public ResponseEntity<?> deposit(@RequestBody Deposit deposit) {
		String response = transactionService.deposit(deposit);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@GetMapping("/balance/{customerId}")
	public ResponseEntity<Double> getBalance(@PathVariable String customerId) {
		System.out.println(customerId);
		Double response = transactionService.getBalance(customerId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
