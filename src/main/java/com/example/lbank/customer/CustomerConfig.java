package com.example.lbank.customer;

import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository repository) {
		return args -> {
			CustomerDetails samuel =  new CustomerDetails(12221222L, "Samuel", "Akin", "sam@gmail.com", "122 exam st", 123221223L, LocalDate.of(1990, 12, 1));
			CustomerDetails adeola = new CustomerDetails("Adeola", "Oke", "adee@gmail.com", "123 calling st", 123456789L,  LocalDate.of(2000, 10, 14));
			CustomerDetails ethan = new CustomerDetails("Ethan", "Colwell", "ethan.colwell@gmail.com", "1003 wagnac cx", 3223322223L, LocalDate.of(2000, 11, 11));
			repository.saveAll(List.of(samuel, adeola, ethan));
		};
	}
}
