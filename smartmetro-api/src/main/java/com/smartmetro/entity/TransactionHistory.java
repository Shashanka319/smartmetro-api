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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "txn_id_seq")
    @SequenceGenerator(name = "txn_id_seq",sequenceName = "txn_history_seq",allocationSize=1)
    @Column(name = "txn_id")
    private Long transactionId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "txn_type")
    private String transactionType;

    @Column(name = "txn_date")
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private  MetroCard metroCard1;
}