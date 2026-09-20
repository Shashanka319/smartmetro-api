package com.smartmetro.service;

import com.smartmetro.repository.RouteRepository;
import com.smartmetro.repository.RouteStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteStationServiceImpl implements RouteStationService {
    @Autowired
    private RouteStationRepository routeStationRepository;

}
