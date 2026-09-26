package com.smartmetro.service;

import com.smartmetro.entity.RouteStation;
import com.smartmetro.exception.RouteStationNotFoundException;
import com.smartmetro.model.RouteStationTO;
import com.smartmetro.repository.RouteStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RouteStationServiceImpl implements RouteStationService {
    @Autowired
    private RouteStationRepository routeStationRepository;

    @Override
    public List<RouteStationTO> findAllRouteStations() throws RouteStationNotFoundException {
        log.info("Inside the RouteStationServiceImpl.findAllUsers");
        List<RouteStation> routeStations = routeStationRepository.findAll();
        if(routeStations.isEmpty()) {
            log.error("RouteStations are not Found");
            throw new RouteStationNotFoundException("RouteStations are Empty");
        }
        List<RouteStationTO> routeStationTOS = routeStations.stream().map(routeStation -> {
            RouteStationTO routeStationTO = new RouteStationTO();
            routeStationTO.setRoute(routeStation.getRoute());
            routeStationTO.setStationId(routeStation.getStationId());
            routeStationTO.setSequenceNo(routeStation.getSequenceNo());
            return routeStationTO;

        }).toList();
        log.info("Total Users Found: {}", routeStationTOS.size());
        return routeStationTOS;
    }
}
