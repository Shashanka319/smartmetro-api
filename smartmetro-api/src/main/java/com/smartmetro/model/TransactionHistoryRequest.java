package com.smartmetro.model;

import java.time.LocalDateTime;

public class TransactionHistoryRequest {
    private Long transactionId;

    private double amount;

    private String transactionType;

    private LocalDateTime transactionDate;
}
