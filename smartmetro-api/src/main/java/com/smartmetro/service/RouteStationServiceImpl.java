package com.smartmetro.service;

import com.smartmetro.entity.RouteStation;
import com.smartmetro.entity.RouteStation.RouteStationPK;
import com.smartmetro.exception.RouteStationNotFoundException;
import com.smartmetro.model.RouteStationTO;
import com.smartmetro.repository.RouteStationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- Fixes: Cannot resolve symbol 'Transactional'

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RouteStationServiceImpl implements RouteStationService {

    private final RouteStationRepository routeStationRepository;

    @Override
    public List<RouteStationTO> findAllRouteStations() throws RouteStationNotFoundException {
        log.info("Inside RouteStationServiceImpl.findAllRouteStations");
        List<RouteStation> routeStations = routeStationRepository.findAll();

        if (routeStations.isEmpty()) {
            log.error("No route stations found");
            throw new RouteStationNotFoundException("RouteStations are Empty");
        }

        List<RouteStationTO> routeStationTOs = routeStations.stream()
                .map(this::mapToTO)
                .toList();

        log.info("Total RouteStations Found: {}", routeStationTOs.size());
        return routeStationTOs;
    }

    @Override
    public RouteStationTO findRouteStationById(Long routeId, Long stationId) throws RouteStationNotFoundException {
        log.info("Inside RouteStationServiceImpl.findRouteStationById for routeId: {} and stationId: {}", routeId, stationId);

        RouteStationPK pk = new RouteStationPK();
        pk.setRoute(routeId);
        pk.setStation(stationId);

        return routeStationRepository.findById(pk)
                .map(this::mapToTO)
                .orElseThrow(() -> {
                    log.error("RouteStation not found for routeId: {} and stationId: {}", routeId, stationId);
                    return new RouteStationNotFoundException(
                            "RouteStation not found for Route: " + routeId + " and Station: " + stationId);
                });
    }

    @Override
    public List<RouteStationTO> findRouteStationsByRouteId(Long routeId) throws RouteStationNotFoundException {
        log.info("Inside RouteStationServiceImpl.findRouteStationsByRouteId for routeId: {}", routeId);
        List<RouteStation> routeStations = routeStationRepository.findByRoute_RouteId(routeId);

        if (routeStations.isEmpty()) {
            log.error("No stations found for Route ID: {}", routeId);
            throw new RouteStationNotFoundException("No route stations found for Route ID: " + routeId);
        }

        return routeStations.stream()
                .map(this::mapToTO)
                .toList();
    }

    @Override
    @Transactional
    public String deleteRouteStationById(Long routeId, Long stationId) throws RouteStationNotFoundException {
        log.info("Inside RouteStationServiceImpl.deleteRouteStationById for routeId: {} and stationId: {}", routeId, stationId);

        RouteStationPK pk = new RouteStationPK();
        pk.setRoute(routeId);
        pk.setStation(stationId);

        RouteStation routeStation = routeStationRepository.findById(pk)
                .orElseThrow(() -> {
                    log.error("RouteStation not found for routeId: {} and stationId: {}", routeId, stationId);
                    return new RouteStationNotFoundException(
                            "RouteStation not found for Route ID: " + routeId + " and Station ID: " + stationId);
                });

        routeStationRepository.delete(routeStation);

        log.info("Successfully deleted RouteStation mapping for routeId: {} and stationId: {}", routeId, stationId);
        return "Successfully deleted RouteStation mapping for Route ID: " + routeId + " and Station ID: " + stationId;
    }

    // Fixes: Cannot resolve method 'mapToTO'
    private RouteStationTO mapToTO(RouteStation routeStation) {
        RouteStationTO to = new RouteStationTO();
        if (routeStation.getRoute() != null) {
            to.setRoute(routeStation.getRoute().getRouteId());
        }
        if (routeStation.getStation() != null) {
            to.setStationId(routeStation.getStation().getStationId());
        }
        to.setSequenceNo(routeStation.getSequenceNo());
        return to;
    }
}