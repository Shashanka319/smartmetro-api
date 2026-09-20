package com.smartmetro.controller;

import com.smartmetro.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;
}
