package com.smartmetro.controller;

import com.smartmetro.entity.RouteStation;
import com.smartmetro.service.RouteStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteStationController {
    @Autowired
    private RouteStationService routeStationService;
}
