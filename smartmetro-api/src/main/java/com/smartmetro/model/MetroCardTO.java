package com.smartmetro.model;

import com.smartmetro.entity.Payment;
import com.smartmetro.entity.TransactionHistory;
import com.smartmetro.entity.Trip;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
public class MetroCardTO {
    private Long cardId;

    private String cardNumber;

    private double balance;

    private String status;

    private LocalDate issuedDate;

     Set<TripTO> trips;

    Set<TransactionHistoryTO> transactionHistories;

     Set<PaymentTO> payments;
}
