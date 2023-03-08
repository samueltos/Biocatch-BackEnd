package com.example.lbank.customer;

import com.example.lbank.transactions.Transactions;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;


@Entity
@Table(name = "customer")
public class Customer implements Serializable{
	@Id
	@SequenceGenerator(
			name = "customer_sequence",
			sequenceName = "customer_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "customer_sequence"
			)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private Long phoneNumber;
	private LocalDate dob;

	private String password;
	@Column(name = "customerId")
	private String customerId;
	@Transient
	private Integer age;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Transactions> transactions;

//	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Balance balance;


	public Customer() {
	}
	public Customer(Long id, String firstName, String lastName, String email, String address, Long phoneNumber,String customerId, LocalDate dob, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.customerId = customerId;
		this.email = email;
		this.password = password;
	}

	public Customer(String firstName, String lastName, String email, String address, Long phoneNumber, String customerId, LocalDate dob, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
		this.email = email;
		this.customerId = customerId;
		this.password = password;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "CustomerDetails{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", address='" + address + '\'' +
				", phoneNumber=" + phoneNumber +
				", dob=" + dob +
				", customerId='" + customerId + '\'' +
				", age=" + age +
				", password=" + password +
				'}';
	}
}
