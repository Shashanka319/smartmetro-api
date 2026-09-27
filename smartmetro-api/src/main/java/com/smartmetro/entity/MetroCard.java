package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "metro_card")
public class MetroCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metro_card_id_seq")
    @SequenceGenerator(name = "metro_card_id_seq", sequenceName = "metro_card_seq", allocationSize = 1)
    @Column(name = "CARD_ID")
    private Long cardId;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ISSUED_DATE")
    private LocalDate issuedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "metroCard")
    private Set<Trip> trips;

    @OneToMany(mappedBy = "metroCard")
    private Set<TransactionHistory> transactionHistories;

}