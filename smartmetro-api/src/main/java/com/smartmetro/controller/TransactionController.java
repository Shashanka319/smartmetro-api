package com.smartmetro.controller;

import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.exception.TransactionHistoryNotFoundException;
import com.smartmetro.model.TransactionHistoryTO;
import com.smartmetro.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping                                 //Read the Data Source
    public ResponseEntity<List<TransactionHistoryTO>> getAllTransactions() {
        log.info("Inside the getAllTransactions method");
        List<TransactionHistoryTO> transactionHistoryTOS =null;
        try{
            transactionHistoryTOS = transactionService.findAllTransactions();
        }
        catch (TransactionHistoryNotFoundException e){
            log.error("Transaction not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//400 error
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
        }
        return new ResponseEntity<>(transactionHistoryTOS,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionHistoryTO> getTransactionById(@PathVariable Long id) {
        log.info("Inside the getTransactionById method for id: {}", id);
        try {
            TransactionHistoryTO TransactionHistoryTO = transactionService.findTransactionById(id);
            return ResponseEntity.ok(TransactionHistoryTO);
        } catch (TransactionHistoryNotFoundException e) {
            log.error("Transactions are  not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching card with id {}: ", id, e); // Added 'e'
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
