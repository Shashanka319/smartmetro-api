package com.smartmetro.repository;

import com.smartmetro.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station,Long> {
}
