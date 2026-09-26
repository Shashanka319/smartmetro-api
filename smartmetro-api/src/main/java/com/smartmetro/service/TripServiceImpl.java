package com.smartmetro.service;

import com.smartmetro.entity.Trip;
import com.smartmetro.exception.TripNotFoundException;
import com.smartmetro.model.TripTO;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TripServiceImpl implements TripService{
    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<TripTO> findAllTrips() throws TripNotFoundException {
        log.info("Inside the TripServiceImpl.findAllTrips() method");
        List<Trip> trips = tripRepository.findAll();
        if(trips.isEmpty()) {
            log.error("Trips are not Found");
            throw new TripNotFoundException("Trips are Empty");
        }
        List<TripTO> tripTOS = trips.stream().map(trip -> {
            TripTO tripTO = new TripTO();
            tripTO.setTripId(trip.getTripId());
            tripTO.setStartTime(trip.getStartTime());
            tripTO.setEndTime(trip.getEndTime());
            tripTO.setFareAmount(trip.getFareAmount());
            return tripTO;

        }).toList();
        log.info("Total Users Found: {}", tripTOS.size());
        return tripTOS;
    }
}
