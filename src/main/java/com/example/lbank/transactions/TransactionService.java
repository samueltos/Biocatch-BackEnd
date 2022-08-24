package com.example.lbank.transactions;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	public final TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	public List<Transactions> getTransactions(){
		return transactionRepository.findAll();
	}

	public void addTransaction(Transactions transactions) {
		transactionRepository.save(transactions);
	}
	
		

}
