package com.smartmetro.repository;

import com.smartmetro.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionHistory,Long> {

}
