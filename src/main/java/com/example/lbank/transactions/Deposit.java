package com.example.lbank.transactions;

import lombok.Data;

@Data
public class Deposit {
    private Long id;
    private String customerId;
    private Double amount;
}
