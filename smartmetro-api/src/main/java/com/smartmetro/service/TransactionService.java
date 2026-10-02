package com.smartmetro.service;

import com.smartmetro.exception.TransactionHistoryNotFoundException;
import com.smartmetro.model.TransactionHistoryTO;

import java.util.List;

public interface TransactionService {
    List<TransactionHistoryTO> findAllTransactions() throws TransactionHistoryNotFoundException;
    TransactionHistoryTO findTransactionById(Long id) throws TransactionHistoryNotFoundException;
}
