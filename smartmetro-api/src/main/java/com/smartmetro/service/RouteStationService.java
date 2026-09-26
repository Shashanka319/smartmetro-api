package com.smartmetro.service;

import com.smartmetro.exception.RouteStationNotFoundException;
import com.smartmetro.exception.StationNotFoundException;
import com.smartmetro.model.RouteStationTO;

import java.util.List;

public interface RouteStationService {
    List<RouteStationTO> findAllRouteStations() throws RouteStationNotFoundException;
}
