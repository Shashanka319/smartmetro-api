package com.smartmetro.service;

import com.smartmetro.entity.RouteStation;
import com.smartmetro.entity.Station;
import com.smartmetro.exception.StationNotFoundException;
import com.smartmetro.model.RouteStationTO;
import com.smartmetro.model.StationRequest;
import com.smartmetro.model.StationTO;
import com.smartmetro.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    @Override
    public List<StationTO> findAllStations() throws StationNotFoundException {
        log.info("Inside the StationServiceImpl.findAllStations");
        List<Station> stations = stationRepository.findAll();

        if (stations==null ||stations.isEmpty()) {
            log.error("No stations found in the database");
            throw new StationNotFoundException("Stations are Empty");
        }

        List<StationTO> stationTOList = stations.stream()
                .map(this::mapToStationTO)
                .toList();

        log.info("Total Stations Found: {}", stationTOList.size());
        return stationTOList;
    }

    @Override
    public StationTO findStationById(Long id) throws StationNotFoundException {
        log.info("Inside the StationServiceImpl.findStationById for id: {}", id);

        return stationRepository.findById(id)
                .map(this::mapToStationTO)
                .orElseThrow(() -> {
                    log.error("Station with ID {} not found", id);
                    return new StationNotFoundException("Station not found for ID: " + id);
                });
    }

    @Override
    public long saveStation(StationRequest stationRequest) throws StationNotFoundException {
        log.info("Inside the StationServiceImpl.saveStation");
        Station station = new  Station();
        if(stationRequest.getStationName() == null || stationRequest.getStationName().isEmpty()) {
            station.setStationName(stationRequest.getStationName());
        }
        if(stationRequest.getStationCode().isEmpty()){
            station.setStationCode(stationRequest.getStationCode());
        }
        if(stationRequest.getLocation() == null || stationRequest.getLocation().isEmpty()) {
            station.setLOCATION(stationRequest.getLocation());
        }
        Station save =  stationRepository.save(station);
        return save.getStationId();
    }

    private StationTO mapToStationTO(Station station) {
        StationTO stationTO = new StationTO();
        stationTO.setStationId(station.getStationId());
        stationTO.setStationName(station.getStationName());
        stationTO.setStationCode(station.getStationCode());
        stationTO.setLocation(station.getLOCATION());

        if (station.getRouteStations() != null && !station.getRouteStations().isEmpty()) {
            List<RouteStationTO> routeStationTOList = station.getRouteStations().stream()
                    .map(this::mapToRouteStationTO)
                    .toList();
            stationTO.setRouteStations(routeStationTOList);
        } else {
            stationTO.setRouteStations(Collections.emptyList());
        }

        return stationTO;
    }

    private RouteStationTO mapToRouteStationTO(RouteStation routeStation) {
        RouteStationTO routeStationTO = new RouteStationTO();
        if (routeStation.getStation() != null) {
            routeStationTO.setStationId(routeStation.getStation().getStationId());
        }
        if (routeStation.getRoute() != null) {
            routeStationTO.setRoute(routeStation.getRoute().getRouteId());
        }
        routeStationTO.setSequenceNo(routeStation.getSequenceNo());
        return routeStationTO;
    }
}