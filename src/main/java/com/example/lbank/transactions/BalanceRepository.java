package com.example.lbank.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

//    @Query("SELECT b FROM Balance b WHERE b.customerId = :customer")
//    @Query("SELECT b FROM Balance ORDER BY date DESC LIMIT 1 WHERE b.customerId= :customer ")
    Balance findTopByCustomerId(String customer);


//    @Query("SELECT b FROM Balance b JOIN FETCH b.customer c WHERE c.customerId = :customerId")
//    Balance findByCustomerUUID(String customerId);
}
