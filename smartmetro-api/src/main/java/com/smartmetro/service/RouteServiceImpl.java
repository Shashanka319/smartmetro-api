package com.smartmetro.service;

import com.smartmetro.entity.Route;
import com.smartmetro.exception.RouteNotFoundException;
import com.smartmetro.model.RouteStationTO;
import com.smartmetro.model.RouteTO;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<RouteTO> findAllRoutes() throws RouteNotFoundException {
        log.info("Inside the RouteServiceImpl.findAllUsers");
        List<Route> routes = routeRepository.findAll();
        if(routes.isEmpty()) {
            log.error("Routes are not Found");
            throw new RouteNotFoundException("Routes are Empty");
        }
        List<RouteTO> routeTOS= routes.stream().map(route -> {
            RouteTO routeTO = new RouteTO();
            routeTO.setRouteId(route.getRouteId());
            routeTO.setRouteName(route.getRouteName());
            if(route.getRouteStations() != null) {
                Set<RouteStationTO> routeStationTOS = route.getRouteStations().stream().map(routeStation -> {
                    RouteStationTO routeStationTO = new RouteStationTO();
                    routeStationTO.setStationId(routeStationTO.getStationId());
                    routeStationTO.setRoute(routeStationTO.getRoute());
                    routeStationTO.setSequenceNo(routeStationTO.getSequenceNo());
                    return routeStationTO;
                }).collect(Collectors.toSet());
                routeTO.setRouteStations(routeStationTOS);
            }
            return routeTO;
        }).toList();
        log.info("Total Users Found: {}", routeTOS.size());
        return routeTOS;
    }

    @Override
    public RouteTO findRouteById(Long id) throws RouteNotFoundException {
        log.info("Inside the RouteServiceImpl.findRouteById");
        Optional<Route> route = routeRepository.findById(id);
        if(route.isEmpty()){
            log.error("Route not found");
            throw new RouteNotFoundException("Route not found");
        }
        RouteTO routeTO = new RouteTO();
        routeTO.setRouteId(route.get().getRouteId());
        routeTO.setRouteName(route.get().getRouteName());
        if(route.get().getRouteStations() != null) {
            Set<RouteStationTO> routeStationTOS = route.get().getRouteStations().stream().map(routeStation -> {
                RouteStationTO routeStationTO = new RouteStationTO();
                routeStationTO.setStationId(routeStationTO.getStationId());
                routeStationTO.setRoute(routeStationTO.getRoute());
                routeStationTO.setSequenceNo(routeStationTO.getSequenceNo());
                return routeStationTO;
            }).collect(Collectors.toSet());
            routeTO.setRouteStations(routeStationTOS);
        }
        return routeTO;
    }

    @Override
    public String deleteRouteById(Long id) throws RouteNotFoundException {
        log.info("Inside the RouteServiceImpl.deleteRouteById");
        Optional<Route> route = routeRepository.findById(id);
        if(route.isEmpty()){
            log.error("Route not found");
            throw new RouteNotFoundException("Route not found");
        }
        routeRepository.deleteById(id);
        return "Successfully Route deleted acc "+id;
    }
}
