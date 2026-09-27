package com.smartmetro.controller;

import com.smartmetro.exception.StationNotFoundException;
import com.smartmetro.exception.TransactionHistoryNotFoundException;
import com.smartmetro.model.StationTO;
import com.smartmetro.model.TransactionHistoryTO;
import com.smartmetro.service.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
