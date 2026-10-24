package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class PaymentRequest {
    private Long paymentId;

    private double amount;

    private String paymentType;

    private String paymentStatus;

    private LocalDateTime paymentDate;
}
