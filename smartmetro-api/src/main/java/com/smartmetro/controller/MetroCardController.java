package com.smartmetro.controller;

import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.exception.PaymentNotFoundException;
import com.smartmetro.model.MetroCardTO;
import com.smartmetro.service.MetroCardService;
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
@RequestMapping("/api/v1/metroCards")
public class MetroCardController {
    @Autowired
    private MetroCardService metroCardService;
    @GetMapping                                 //Read the Data Source
    public ResponseEntity<List<MetroCardTO>> getAllMetroCards() {
        log.info("Inside the getAllMetroCards method");
        List<MetroCardTO> metroCardTOS =null;
        try{
            metroCardTOS =metroCardService.findAllMetroCards();
        }
        catch (MetroCardNotFoundException e){
            log.error("MetroCard is  not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//400 error
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
        }
        return new ResponseEntity<>(metroCardTOS,HttpStatus.OK);
    }
}
