package com.example.lbank.transactions;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

	@Bean
	CommandLineRunner commandLineRunner(TransactionRepository transactionRepository) {
		return args -> {
			Transactions gas =	new Transactions(12000L, "purchase at exOnOil", 23.89, 230.00);
			transactionRepository.saveAll(List.of(gas));
		};
	}
}
