package com.example.lbank.transactions;


import com.example.lbank.customer.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "Transactions")
public class Transactions implements Serializable{
	@Id
	@SequenceGenerator(
		name = "transaction_sequence",
		sequenceName = "transaction_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "transaction_sequence"
			)	
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "customerId")
	private Customer customer;
	private LocalDate date;
	private String description;
	private Double amount;

	@Transient
	private Double balance;


	public Transactions(){
	}
	public Transactions(String description, double price, double balance, LocalDate date) {
		this.description = description;
		this.amount = price;
		this.balance = balance;
		this.date = date;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerUUID() {
		return customer.getCustomerId();
	}
	public void setCustomerUUID(String customer) {
		this.customer.setCustomerId(customer);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transactions [id=" + id + ", customerId=" + customer.getCustomerId() + ", description=" + description + ", amount="
				+ amount + ", date=" + date + ", balance=" + balance + "]";
	}
	
	
	

}
