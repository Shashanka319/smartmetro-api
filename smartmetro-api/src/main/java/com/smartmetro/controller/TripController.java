package com.smartmetro.controller;

import com.smartmetro.exception.StationNotFoundException;
import com.smartmetro.exception.TripNotFoundException;
import com.smartmetro.model.TripTO;
import com.smartmetro.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @GetMapping                                 //Read the Data Source
    public ResponseEntity<List<TripTO>> getAllTrips() {
        log.info("Inside the getAllTrip method");
        List<TripTO> tripTo=null;
        try{
            tripTo=tripService.findAllTrips();
        }
        catch (TripNotFoundException e){
            log.error("Trip is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//400 error
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
        }
        return new ResponseEntity<>( tripTo,HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<TripTO> getTripById(@PathVariable Long id) {
        log.info("Inside the getTripById method for id: {}", id);
        try {
            TripTO tripTO = tripService.findTripById(id);
            return ResponseEntity.ok(tripTO);
        } catch (TripNotFoundException e) {
            log.error("Trips is not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching card with id {}: ", id, e); // Added 'e'
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
