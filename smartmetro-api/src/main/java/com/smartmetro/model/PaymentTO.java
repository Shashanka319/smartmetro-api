package com.smartmetro.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PaymentTO {
    private Long paymentId;

    private double amount;

    private String paymentType;

    private String paymentStatus;

    private LocalDateTime paymentDate;
}
