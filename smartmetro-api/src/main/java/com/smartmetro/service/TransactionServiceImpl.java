package com.smartmetro.service;

import com.smartmetro.entity.TransactionHistory;
import com.smartmetro.exception.TransactionHistoryNotFoundException;
import com.smartmetro.model.TransactionHistoryTO;
import com.smartmetro.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionHistoryTO> findAllTransactions() throws TransactionHistoryNotFoundException {
        log.info("Inside the TransactionHistrory.findAllUsers");
        List<TransactionHistory> transcations = transactionRepository.findAll();
        if(transcations.isEmpty()) {
            log.error("Transactions are not Found");
            throw new TransactionHistoryNotFoundException("Transactions are Empty");
        }
        List<TransactionHistoryTO> transactionHistoryTOS = transcations.stream().map(tnx -> {
            TransactionHistoryTO transactionHistoryTO = new TransactionHistoryTO();
            transactionHistoryTO.setTransactionId(tnx.getTransactionId());
            transactionHistoryTO.setAmount(tnx.getAmount());
            transactionHistoryTO.setTransactionType(tnx.getTransactionType());
            transactionHistoryTO.setTransactionDate(tnx.getTransactionDate());
            return transactionHistoryTO;

        }).toList();
        log.info("Total Transcations Found: {}", transactionHistoryTOS.size());
        return transactionHistoryTOS;
    }

    @Override
    public TransactionHistoryTO findTransactionById(Long id) throws TransactionHistoryNotFoundException {
        log.info("Inside the TransactionHistory.findTransactionById");
        Optional<TransactionHistory> transactionHistory = transactionRepository.findById(id);
        if(transactionHistory.isEmpty()) {
            log.error("TransactionHistory not found");
            throw new TransactionHistoryNotFoundException("TransactionHistory not found");
        }
        TransactionHistory transactionHistory1 = transactionHistory.get();
        TransactionHistoryTO transactionHistoryTO = new TransactionHistoryTO();
        transactionHistoryTO.setTransactionId(transactionHistory1.getTransactionId());
        transactionHistoryTO.setAmount(transactionHistory1.getAmount());
        transactionHistoryTO.setTransactionType(transactionHistory1.getTransactionType());
        transactionHistoryTO.setTransactionDate(transactionHistory1.getTransactionDate());

        return transactionHistoryTO;
    }
}
