package com.smartmetro.controller;

import com.smartmetro.exception.TransactionHistoryNotFoundException;
import com.smartmetro.model.TransactionHistoryTO;
import com.smartmetro.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping                                 //Read the Data Source
    public ResponseEntity<List<TransactionHistoryTO>> getAllUsers() {
        log.info("Inside the getAllUsers method");
        List<TransactionHistoryTO> transactionHistoryTOS =null;
        try{
            transactionHistoryTOS = transactionService.findAllTransactions();
        }
        catch (TransactionHistoryNotFoundException e){
            log.error("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//400 error
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
        }
        return new ResponseEntity<>(transactionHistoryTOS,HttpStatus.OK);

    }
}
