package com.smartmetro.service;

import com.smartmetro.exception.PaymentNotFoundException;
import com.smartmetro.model.PaymentTO;

import java.util.List;

public interface PaymentService {
    List<PaymentTO> findAllPayments() throws PaymentNotFoundException;
}
