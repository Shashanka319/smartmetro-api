package com.smartmetro.controller;

import com.smartmetro.exception.PaymentNotFoundException;
import com.smartmetro.exception.RouteNotFoundException;
import com.smartmetro.model.PaymentTO;
import com.smartmetro.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping                                 //Read the Data Source
    public ResponseEntity<List<PaymentTO>> getAllPayments() {
        log.info("Inside the getAllPayments method");
        List<PaymentTO> paymentTOS =null;
        try{
            paymentTOS =paymentService.findAllPayments();
        }
        catch (PaymentNotFoundException e){
            log.error("Payment not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//400 error
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
        }
        return new ResponseEntity<>(paymentTOS,HttpStatus.OK);
    }
}
