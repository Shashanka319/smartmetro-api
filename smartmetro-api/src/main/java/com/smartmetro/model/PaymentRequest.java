package com.smartmetro.model;

import java.time.LocalDateTime;

public class PaymentRequest {
    private Long paymentId;

    private double amount;

    private String paymentType;

    private String paymentStatus;

    private LocalDateTime paymentDate;
}
