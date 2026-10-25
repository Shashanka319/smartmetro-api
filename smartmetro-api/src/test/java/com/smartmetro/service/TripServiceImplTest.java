package com.smartmetro.service;

import com.smartmetro.entity.Trip;
import com.smartmetro.exception.TripNotFoundException;
import com.smartmetro.model.TripTO;
import com.smartmetro.repository.TripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceImplTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripServiceImpl tripServiceImpl;

    @Test
    public void testGetAllTrips_whenTripsExist_thenReturnData() throws TripNotFoundException {
        List<Trip> tripList = new ArrayList<>();

        Trip trip1 = new Trip();
        trip1.setTripId(1L);
        trip1.setFareAmount(30.0);
        tripList.add(trip1);

        Trip trip2 = new Trip();
        trip2.setTripId(2L);
        trip2.setFareAmount(45.0);
        tripList.add(trip2);

        when(tripRepository.findAll()).thenReturn(tripList);

        List<TripTO> tripTOS = tripServiceImpl.findAllTrips();
        assertEquals(2, tripTOS.size());
    }

    @Test
    public void testGetAllTrips_whenTripsNotExist_thenThrowException() {
        List<Trip> tripList = null;
        when(tripRepository.findAll()).thenReturn(tripList);

        assertThrows(TripNotFoundException.class, () -> tripServiceImpl.findAllTrips());
    }
}