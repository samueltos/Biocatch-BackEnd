package com.example.lbank.transactions;

import com.example.lbank.Exception.UserNotFoundException;
import com.example.lbank.customer.Customer;
import com.example.lbank.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class TransactionService {
	public final TransactionRepository transactionRepository;
	public final BalanceRepository balanceRepository;
	private final CustomerRepository customerRepository;

	@Autowired
	public TransactionService(TransactionRepository transactionRepository, BalanceRepository balanceRepository, CustomerRepository customerRepository) {
		this.transactionRepository = transactionRepository;
		this.balanceRepository = balanceRepository;
		this.customerRepository = customerRepository;
	}
	
	public Set<Transactions> getTransactions(Customer customer){
		String customerUUID = customer.getCustomerId();
		return transactionRepository.findAllByCustomer(customerUUID);
	}

	public Transactions addTransaction(TransactionDTO transactionDTO, Boolean isDeposit) {

		Customer customer = getCustomer(transactionDTO.getCustomerId());

		Transactions transactions = new Transactions();
		transactions.setCustomer(customer);
		transactions.setDescription(transactionDTO.getDescription());
		transactions.setDate(transactionDTO.getDate());
		transactions.setAmount(transactionDTO.getAmount());

		Balance balance = balanceRepository.findTopByCustomerId(transactionDTO.getCustomerId());
		Double currentBalance = balance.getBalance();
		Double nBalance;
		if(isDeposit){
			nBalance = currentBalance + transactions.getAmount();
		}else{
			nBalance = currentBalance - transactions.getAmount();

			Balance newBalance = new Balance(balance.getId(), customer.getCustomerId(), nBalance);
			balanceRepository.save(newBalance);
		}
		transactions.setBalance(nBalance);

		return transactionRepository.save(transactions);
	}


	public Boolean checkBalance(Double price, String customer){
		Balance balance = balanceRepository.findTopByCustomerId(customer);
		return balance.getBalance() > price;
	}

	public String deposit(Deposit deposit){
		String customerId = deposit.getCustomerId();
		Double amount = deposit.getAmount();
		Balance customerBalance = balanceRepository.findTopByCustomerId(customerId);

		Balance newBalance = new Balance();
		Customer customer = getCustomer(deposit.getCustomerId());
		if(customerBalance == null){
			newBalance.setBalance(amount);
			newBalance.setCustomerId(customer.getCustomerId());
			balanceRepository.save(newBalance);
		}else{
			Double nBalance = customerBalance.getBalance() + amount;
			customerBalance.setBalance(nBalance);
			newBalance.setBalance(nBalance);
			balanceRepository.save(newBalance);
		}

		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setCustomerId(customerId);
		transactionDTO.setAmount(amount);
		transactionDTO.setDescription("Deposit");
		transactionDTO.setDate(LocalDate.now());
		addTransaction(transactionDTO, true);

		return "Deposit completed";
	}

	public Double getBalance(String customerId){
		Balance balance = balanceRepository.findTopByCustomerId(customerId);
		if(balance == null){
			return 0.00;
		}
		return  balance.getBalance();
	}

	private Customer getCustomer(@Param("customerId") String customerId){
		Customer customer = customerRepository.findByCustomerUUID(customerId)
				.orElseThrow(() -> new UserNotFoundException("No customer found with UUID - " + customerId));
		return customer;
	}

}
