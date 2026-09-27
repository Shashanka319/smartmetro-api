package com.smartmetro.controller;

import com.smartmetro.exception.MetroCardNotFoundException;
import com.smartmetro.model.MetroCardTO;
import com.smartmetro.service.MetroCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/metroCards")
public class MetroCardController {

    @Autowired
    private MetroCardService metroCardService;

    @GetMapping
    public ResponseEntity<List<MetroCardTO>> getAllMetroCards() {
        log.info("Inside the getAllMetroCards method");
        try {
            List<MetroCardTO> metroCardTOS = metroCardService.findAllMetroCards();
            return ResponseEntity.ok(metroCardTOS);
        } catch (MetroCardNotFoundException e) {
            log.error("MetroCard not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching cards: ", e); // Added 'e' to see stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<MetroCardTO> getMetroCardById(@PathVariable Long id) {
        log.info("Inside the getMetroCardById method for id: {}", id);
        try {
            MetroCardTO metroCardTO = metroCardService.findMetroCardById(id);
            return ResponseEntity.ok(metroCardTO);
        } catch (MetroCardNotFoundException e) {
            log.error("MetroCard not found with id: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Exception occurred while fetching card with id {}: ", id, e); // Added 'e'
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}