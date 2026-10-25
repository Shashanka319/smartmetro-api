package com.smartmetro.service;

import com.smartmetro.entity.Payment;
import com.smartmetro.exception.PaymentNotFoundException;
import com.smartmetro.model.PaymentTO;
import com.smartmetro.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentServiceImpl;

    @Test
    public void testGetAllPayments_whenPaymentsExist_thenReturnData() throws PaymentNotFoundException {
        List<Payment> paymentList = new ArrayList<>();

        Payment payment1 = new Payment();
        payment1.setPaymentId(501L);
        payment1.setAmount(100.0);
        payment1.setPaymentType("UPI");
        payment1.setPaymentStatus("SUCCESS");
        paymentList.add(payment1);

        Payment payment2 = new Payment();
        payment2.setPaymentId(502L);
        payment2.setAmount(200.0);
        payment2.setPaymentType("NET_BANKING");
        payment2.setPaymentStatus("SUCCESS");
        paymentList.add(payment2);

        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<PaymentTO> paymentTOS = paymentServiceImpl.findAllPayments();
        assertEquals(2, paymentTOS.size());
    }

    @Test
    public void testGetAllPayments_whenPaymentsNotExist_thenThrowException() {
        List<Payment> paymentList = null;
        when(paymentRepository.findAll()).thenReturn(paymentList);

        assertThrows(PaymentNotFoundException.class, () -> paymentServiceImpl.findAllPayments());
    }
}