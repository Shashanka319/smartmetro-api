package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class TransactionHistoryRequest {
    private Long transactionId;

    private double amount;

    private String transactionType;

    private LocalDateTime transactionDate;
}
