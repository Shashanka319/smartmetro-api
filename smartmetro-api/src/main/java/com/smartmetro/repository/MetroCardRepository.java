package com.smartmetro.repository;

import com.smartmetro.entity.MetroCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MetroCardRepository extends JpaRepository<MetroCard,Long> {
}
