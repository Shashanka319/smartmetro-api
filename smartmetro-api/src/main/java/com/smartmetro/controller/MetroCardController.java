package com.smartmetro.controller;

import com.smartmetro.service.MetroCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetroCardController {
    @Autowired
    private MetroCardService metroCardService;
}
