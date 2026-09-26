package com.smartmetro.controller;

import com.smartmetro.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {
    @Autowired
    private StationService stationService;
}
