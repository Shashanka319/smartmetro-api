package com.smartmetro.service;

import com.smartmetro.entity.Station;
import com.smartmetro.exception.StationNotFoundException;
import com.smartmetro.model.RouteStationTO;
import com.smartmetro.model.StationTO;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.StationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StationServiceImpl implements StationService {
    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<StationTO> findAllStations() throws StationNotFoundException {
        log.info("Inside the StaionServiceImpl.findAllUsers");
        List<Station> stations = stationRepository.findAll();
        if(stations.isEmpty()) {
            log.error("Stations are not Found");
            throw new StationNotFoundException("Stations are Empty");
        }
        List<StationTO> stationTOS = stations.stream().map(station -> {
            StationTO stationTO = new StationTO();
            stationTO.setStationId(station.getStationId());
            stationTO.setStationName(station.getStationName());
            stationTO.setStationCode(station.getStationCode());
            stationTO.setLocation(station.getLOCATION());
            if(station.getRouteStations() != null) {
                Set<RouteStationTO> routeStationTOSet = station.getRouteStations().stream().map(routeStation->{
                    RouteStationTO routeStationTO = new RouteStationTO();
                    routeStationTO.setStationId(routeStation.getStation().getStationId());
                    routeStationTO.setRoute(routeStation.getRoute().getRouteId());
                    return routeStationTO;
                }).collect(Collectors.toSet());
                stationTO.getRouteStations().addAll(routeStationTOSet);
            }
            return stationTO;

        }).toList();
        log.info("Total Stations Found: {}", stationTOS.size());
        return stationTOS;
    }
}
