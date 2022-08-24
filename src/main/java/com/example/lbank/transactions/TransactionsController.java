package com.example.lbank.transactions;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/customer/transactions")
public class TransactionsController {

	TransactionService transactionService;
	
	@Autowired
	public TransactionsController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@GetMapping
	public List<Transactions> getTransaction() {
		return transactionService.getTransactions();
	}
	
	@PostMapping
	public void postTransaction(@RequestBody Transactions transactions) {
		transactionService.addTransaction(transactions);		
	}
}
