package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq",sequenceName = "payment_seq",allocationSize=1)
    @Column(name = "payment_id")
    private Long paymentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
    private MetroCard metroCard;

    @Column(name = "amount")
    private double amount;

    @Column(name = "payment_method")
    private String paymentType;

    @Column(name = "status")
    private String paymentStatus;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
}