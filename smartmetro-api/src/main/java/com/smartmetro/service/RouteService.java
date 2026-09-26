package com.smartmetro.service;

import com.smartmetro.exception.RouteNotFoundException;
import com.smartmetro.exception.RouteStationNotFoundException;
import com.smartmetro.model.RouteTO;

import java.util.List;

public interface RouteService {
    List<RouteTO> findAllRoutes() throws RouteNotFoundException;
}
