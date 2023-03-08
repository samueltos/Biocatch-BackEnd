package com.example.lbank.transactions;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    private String customerId;
    private String description;
    private Double amount;
    private LocalDate date;
}
