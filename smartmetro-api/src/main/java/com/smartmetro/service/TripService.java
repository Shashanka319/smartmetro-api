package com.smartmetro.service;

import com.smartmetro.exception.TripNotFoundException;
import com.smartmetro.model.TripTO;

import java.util.List;

public interface TripService{
    List<TripTO> findAllTrips() throws TripNotFoundException;
}
