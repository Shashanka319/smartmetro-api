package com.smartmetro.controller;

import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.exception.StationNotFoundException;
import com.smartmetro.model.StationTO;
import com.smartmetro.service.StationService;
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
@RequestMapping("/api/v1/stations")
public class StationController {
    @Autowired
    private StationService stationService;
    @GetMapping                                 //Read the Data Source
    public ResponseEntity<List<StationTO>> getAllStations() {
        log.info("Inside the getAllStations method");
        List<StationTO> stationTo =null;
        try{
            stationTo = stationService.findAllStations();
        }
        catch (StationNotFoundException e){
            log.error("Station not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//400 error
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
        }
        return new ResponseEntity<>(stationTo,HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<StationTO> getStationById(@PathVariable Long id) {
        log.info("Inside the getMetroCardById method for id: {}", id);
        try {
            StationTO stationTO = stationService.findStationById(id);
            return ResponseEntity.ok(stationTO);
        } catch (StationNotFoundException e) {
            log.error("Station is not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching card with id {}: ", id, e); // Added 'e'
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
