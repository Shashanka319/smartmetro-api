package com.smartmetro.controller;

import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.exception.RouteNotFoundException;
import com.smartmetro.model.RouteTO;
import com.smartmetro.service.RouteService;
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
@RequestMapping("/api/v1/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @GetMapping                                 //Read the Data Source
    public ResponseEntity<List<RouteTO>> getAllRoutes() {
        log.info("Inside the getAllRoutes method");
        List<RouteTO> routeTOS =null;
        try{
            routeTOS =routeService.findAllRoutes();
        }
        catch (RouteNotFoundException e){
            log.error("Route not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//400 error
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
        }
        return new ResponseEntity<>(routeTOS,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RouteTO> getRouteById(@PathVariable Long id) {
        log.info("Inside the getRouteById method for id: {}", id);
        try {
            RouteTO routeTO = routeService.findRouteById(id);
            return ResponseEntity.ok(routeTO);
        } catch (RouteNotFoundException e) {
            log.error("Routes not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching card with id {}: ", id, e); // Added 'e'
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
