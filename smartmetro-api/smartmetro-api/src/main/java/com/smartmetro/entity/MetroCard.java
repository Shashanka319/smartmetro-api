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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "metro_card_id_seq")
    @SequenceGenerator(name = "metro_card_id_seq",sequenceName = "metro_card_seq",allocationSize=1)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "balance")
    private double balance;

    @Column(name = "status")
    private String status;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user1;

    @OneToMany(mappedBy = "metroCard")
    private Set<Trip> trips;

    @OneToMany
    private Set<TransactionHistory> transactionHistories;

    @OneToMany(mappedBy = "metroCard1")
    private Set<TransactionHistory> transactionHistories1;

    @OneToOne
    private Station station;
}