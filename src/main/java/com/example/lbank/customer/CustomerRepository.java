package com.example.lbank.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("from Customer c where c.customerId = :customerId")
    Optional<Customer> findByCustomerUUID(@Param("customerId") String customerId);

    Optional<Customer> findByEmail(String email);

}
