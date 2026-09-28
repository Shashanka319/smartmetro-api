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
    @Column(name = "PAYMENT_ID ")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID  ", referencedColumnName = "card_id")
    private MetroCard metroCard;

    @Column(name = "AMOUNT ")
    private double amount;

    @Column(name = "PAYMENT_METHOD")
    private String paymentType;

    @Column(name = "STATUS")
    private String paymentStatus;

    @Column(name = "PAYMENT_DATE ")
    private LocalDateTime paymentDate;
}