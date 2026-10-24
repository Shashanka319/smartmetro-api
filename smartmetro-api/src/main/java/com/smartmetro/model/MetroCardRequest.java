package com.smartmetro.model;

import java.time.LocalDate;
import java.util.Set;

public class MetroCardRequest {
    private Long cardId;

    private String cardNumber;

    private double balance;

    private String status;

    private LocalDate issuedDate;

    Set<TripTO> trips;

    Set<TransactionHistoryTO> transactionHistories;

    Set<PaymentTO> payments;
}
