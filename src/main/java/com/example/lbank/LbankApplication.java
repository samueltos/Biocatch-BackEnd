package com.example.lbank;

import com.example.lbank.customer.Customer;
import com.example.lbank.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class LbankApplication implements CommandLineRunner{

	@Autowired
	private CustomerRepository customerRepository;

	public LbankApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Customer samuel =  new Customer(12221222L, "Samuel", "Akin", "sam@gmail.com", "122 exam st", 123221223L, "443344442KJL", LocalDate.of(1990, 12, 1), "jkls23443");
		Customer adeola = new Customer("Adeola", "Oke", "adee@gmail.com", "123 calling st", 123456789L, "1334443343D", LocalDate.of(2000, 10, 14), "lKo3akdds");
		Customer ethan = new Customer("Ethan", "Colwell", "ethan.colwell@gmail.com", "1003 wagnac cx", 3223322223L, "90323454854JDS", LocalDate.of(2000, 11, 11), "923sjdkasa");
		customerRepository.saveAll(List.of(samuel, adeola, ethan));
	}

	public static void main(String[] args) {
		SpringApplication.run(LbankApplication.class, args);

	}

	public WebMvcConfigurer webMvcConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
						.allowedOrigins("*")
						.allowedHeaders("*");
			}
		};
	}


}
