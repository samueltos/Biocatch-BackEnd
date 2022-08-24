package com.example.lbank.transactions;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
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
	
	@OneToMany(mappedBy = "id")
	private Long customerId;
	private String description;
	private double price;
	private double balance;


	public Transactions(Long customerId, String description, double price, double balance) {
		this.customerId = customerId;
		this.description = description;
		this.price = price;
		this.balance = balance;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transactions [id=" + id + ", customerId=" + customerId + ", description=" + description + ", price="
				+ price + ", balance=" + balance + "]";
	}
	
	
	

}
