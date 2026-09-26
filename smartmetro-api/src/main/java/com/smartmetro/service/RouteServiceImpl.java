package com.smartmetro.service;

import com.smartmetro.entity.Route;
import com.smartmetro.exception.RouteNotFoundException;
import com.smartmetro.model.RouteTO;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            return routeTO;

        }).toList();
        log.info("Total Users Found: {}", routeTOS.size());
        return routeTOS;
    }
}
