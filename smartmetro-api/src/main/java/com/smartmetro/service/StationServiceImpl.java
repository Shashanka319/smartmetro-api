package com.smartmetro.service;

import com.smartmetro.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    private StationRepository stationRepository;
}
