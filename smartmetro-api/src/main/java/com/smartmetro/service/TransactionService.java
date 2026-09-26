package com.smartmetro.service;

import com.smartmetro.exception.TransactionHistoryNotFoundException;
import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.TransactionHistoryTO;
import com.smartmetro.model.TripTO;

import java.util.List;

public interface TransactionService {
    List<TransactionHistoryTO> findAllTransactions() throws TransactionHistoryNotFoundException;
}
