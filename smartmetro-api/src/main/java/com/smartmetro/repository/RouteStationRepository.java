package com.smartmetro.repository;

import com.smartmetro.entity.RouteStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteStationRepository extends JpaRepository<RouteStation, RouteStation.RouteStationPK> {
    List<RouteStation> findByRoute_RouteId(Long routeId);
}