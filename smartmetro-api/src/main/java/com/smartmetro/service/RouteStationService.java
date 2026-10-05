package com.smartmetro.service;

import com.smartmetro.exception.RouteStationNotFoundException;
import com.smartmetro.model.RouteStationTO;
import java.util.List;

public interface RouteStationService {
    List<RouteStationTO> findAllRouteStations() throws RouteStationNotFoundException;
    RouteStationTO findRouteStationById(Long routeId, Long stationId) throws RouteStationNotFoundException;
    List<RouteStationTO> findRouteStationsByRouteId(Long routeId) throws RouteStationNotFoundException;
    String deleteRouteStationById(Long routeId, Long stationId) throws RouteStationNotFoundException;
}