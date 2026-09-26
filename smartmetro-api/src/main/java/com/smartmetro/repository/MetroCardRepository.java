package com.smartmetro.repository;

import com.smartmetro.entity.MetroCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetroCardRepository extends JpaRepository<MetroCard,Long> {
}
