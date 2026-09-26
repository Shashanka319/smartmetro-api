package com.smartmetro.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TransactionHistoryTO {
    private Long transactionId;

    private double amount;

    private String transactionType;

    private LocalDateTime transactionDate;
}
