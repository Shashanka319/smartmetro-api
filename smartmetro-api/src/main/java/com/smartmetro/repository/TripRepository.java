package com.smartmetro.repository;

import com.smartmetro.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long> {
}
