package com.smartmetro.repository;

import com.smartmetro.entity.RouteStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteStationRepository extends JpaRepository<RouteStation, Long> {
}