package com.smartmetro.service;

import com.smartmetro.exception.StationNotFoundException;
import com.smartmetro.model.StationTO;

import java.util.List;

public interface StationService {
    List<StationTO> findAllStations() throws StationNotFoundException;
}
