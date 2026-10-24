package com.smartmetro.service;

import com.smartmetro.exception.StationNotFoundException;
import com.smartmetro.model.StationRequest;
import com.smartmetro.model.StationTO;

import java.util.List;

public interface StationService {
    List<StationTO> findAllStations() throws StationNotFoundException;
    StationTO findStationById(Long id) throws StationNotFoundException;
    long saveStation(StationRequest stationRequest) throws StationNotFoundException;
}
