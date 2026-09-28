package com.smartmetro.service;

import com.smartmetro.entity.Payment;
import com.smartmetro.exception.PaymentNotFoundException;
import com.smartmetro.model.PaymentTO;
import com.smartmetro.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl  implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<PaymentTO> findAllPayments() throws PaymentNotFoundException {
        log.info("Inside the PaymentServiceImpl.findAllUsers");
        List<Payment> payments = paymentRepository.findAll();
        if(payments.isEmpty()) {
            log.error("Users are not Found");
            throw new PaymentNotFoundException("Users are Empty");
        }
        List<PaymentTO> paymentTOS = payments.stream().map(payment -> {
            PaymentTO paymentTO = new PaymentTO();
            paymentTO.setPaymentId(payment.getPaymentId());
            paymentTO.setAmount(payment.getAmount());
            paymentTO.setPaymentType(payment.getPaymentType());
            paymentTO.setPaymentStatus(payment.getPaymentStatus());
            paymentTO.setPaymentDate(payment.getPaymentDate());
            return paymentTO;

        }).toList();
        log.info("Total Payments Found: {}", paymentTOS.size());
        return paymentTOS;
    }

    @Override
    public PaymentTO findPaymentById(Long paymentId) throws PaymentNotFoundException {
        log.info("Inside the PaymentServiceImpl.findPaymentById");
        Optional<Payment> payments = paymentRepository.findById(paymentId);
        if(payments.isEmpty()) {
            log.error("Payment not found");
            throw new PaymentNotFoundException("Payment not found");
        }
        PaymentTO paymentTO = new PaymentTO();
        paymentTO.setPaymentId(payments.get().getPaymentId());
        paymentTO.setAmount(payments.get().getAmount());
        paymentTO.setPaymentType(payments.get().getPaymentType());
        paymentTO.setPaymentStatus(payments.get().getPaymentStatus());
        paymentTO.setPaymentDate(payments.get().getPaymentDate());
        return paymentTO;
    }
}
