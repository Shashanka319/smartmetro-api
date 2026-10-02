package com.smartmetro.controller;

import com.smartmetro.entity.RouteStation;
import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.exception.RouteStationNotFoundException;
import com.smartmetro.model.RouteStationTO;
import com.smartmetro.service.RouteStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/routeStations")
public class RouteStationController {
    @Autowired
    private RouteStationService routeStationService;
    @GetMapping                                 //Read the Data Source
    public ResponseEntity<List<RouteStationTO>> getAllRouteStations() {
        log.info("Inside the getAllRouteStations method");
        List<RouteStationTO> routeStationTOS =null;
        try{
            routeStationTOS =routeStationService.findAllRouteStations();
        }
        catch (RouteStationNotFoundException e){
            log.error("RouteStation not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//400 error
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
        }
        return new ResponseEntity<>(routeStationTOS,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RouteStationTO> getRouteStationById(@PathVariable Long id) {
        log.info("Inside the getRouteStationById method for id: {}", id);
        try {
            RouteStationTO routeStationTO = routeStationService.findRouteStationById(id);
            return ResponseEntity.ok(routeStationTO);
        } catch (RouteStationNotFoundException e) {
            log.error("RouteStation is  not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching card with id {}: ", id, e); // Added 'e'
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
