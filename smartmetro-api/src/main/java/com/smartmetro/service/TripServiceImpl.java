package com.smartmetro.service;

import com.smartmetro.entity.Trip;
import com.smartmetro.exception.TripNotFoundException;
import com.smartmetro.model.TripTO;
import com.smartmetro.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public TripTO findTripById(Long id) throws TripNotFoundException {
        log.info("Inside the TripServiceImpl.findTripById() method");
        Optional<Trip> trip = tripRepository.findById(id);
        if(trip == null) {
            log.error("Trip Not Found");
            throw new TripNotFoundException("Trip Not Found");
        }
        Trip trips = trip.get();
        TripTO tripTO = new TripTO();
        tripTO.setTripId(trips.getTripId());
        tripTO.setStartTime(trips.getStartTime());
        tripTO.setEndTime(trips.getEndTime());
        tripTO.setFareAmount(trips.getFareAmount());

        return tripTO;
    }
}
