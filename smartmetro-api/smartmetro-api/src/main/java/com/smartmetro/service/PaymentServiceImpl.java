package com.smartmetro.service;

import com.smartmetro.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl  implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
}
