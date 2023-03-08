package com.example.lbank.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    @Query("SELECT t FROM Transactions t JOIN FETCH t.customer c WHERE c.customerId = :customer")
    Set<Transactions> findAllByCustomer(@Param("customer") String customer);

    @Query("SELECT t FROM Transactions t JOIN FETCH t.customer c WHERE c.customerId = :customer")
    Transactions findByCustomerUUID(@Param("customer") String customer);

}
