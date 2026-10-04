package com.smartmetro.controller;

import com.smartmetro.exception.RouteStationNotFoundException;
import com.smartmetro.model.RouteStationTO;
import com.smartmetro.service.RouteStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class RouteStationController {

    private final RouteStationService routeStationService;

    // GET /api/v1/routeStations
    @GetMapping
    public ResponseEntity<List<RouteStationTO>> getAllRouteStations() {
        log.info("Inside getAllRouteStations endpoint");
        try {
            List<RouteStationTO> routeStationTOs = routeStationService.findAllRouteStations();
            return ResponseEntity.ok(routeStationTOs);
        } catch (RouteStationNotFoundException e) {
            log.error("RouteStations not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching all route stations: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /api/v1/routeStations/route/{routeId}/station/{stationId}
    @GetMapping("/route/{routeId}/station/{stationId}")
    public ResponseEntity<RouteStationTO> getRouteStationById(
            @PathVariable Long routeId,
            @PathVariable Long stationId) {
        log.info("Inside getRouteStationById endpoint for routeId: {} and stationId: {}", routeId, stationId);
        try {
            RouteStationTO routeStationTO = routeStationService.findRouteStationById(routeId, stationId);
            return ResponseEntity.ok(routeStationTO);
        } catch (RouteStationNotFoundException e) {
            log.error("RouteStation not found for routeId: {} and stationId: {}", routeId, stationId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching route station: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // GET /api/v1/routeStations/route/{routeId}
    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<RouteStationTO>> getRouteStationsByRouteId(@PathVariable Long routeId) {
        log.info("Inside getRouteStationsByRouteId endpoint for routeId: {}", routeId);
        try {
            List<RouteStationTO> routeStations = routeStationService.findRouteStationsByRouteId(routeId);
            return ResponseEntity.ok(routeStations);
        } catch (RouteStationNotFoundException e) {
            log.error("Route stations not found for routeId: {}", routeId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching route stations for routeId: {}", routeId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}