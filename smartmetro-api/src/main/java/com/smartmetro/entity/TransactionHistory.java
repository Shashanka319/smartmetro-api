package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "txn_id_seq")
    @SequenceGenerator(name = "txn_id_seq", sequenceName = "txn_history_seq", allocationSize = 1)
    @Column(name = "TXN_ID")
    private Long transactionId;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "TXN_TYPE")
    private String transactionType;

    @Column(name = "TXN_DATE")
    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private MetroCard metroCard;

}