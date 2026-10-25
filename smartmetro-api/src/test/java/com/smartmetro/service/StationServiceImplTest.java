package com.smartmetro.service;

import com.smartmetro.entity.Station;
import com.smartmetro.exception.StationNotFoundException;
import com.smartmetro.model.StationTO;
import com.smartmetro.repository.StationRepository;
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
public class StationServiceImplTest {

    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private StationServiceImpl stationServiceImpl;

    @Test
    public void testGetAllStations_whenStationsExist_thenReturnData() throws StationNotFoundException {
        List<Station> stationList = new ArrayList<>();

        Station station1 = new Station();
        station1.setStationId(101L);
        station1.setStationName("Majestic");
        station1.setLOCATION("Central Bengaluru");
        stationList.add(station1);

        Station station2 = new Station();
        station2.setStationId(102L);
        station2.setStationName("Indiranagar");
        station2.setLOCATION("East Bengaluru");
        stationList.add(station2);

        when(stationRepository.findAll()).thenReturn(stationList);

        List<StationTO> stationTOS = stationServiceImpl.findAllStations();
        assertEquals(2, stationTOS.size());
    }

    @Test
    public void testGetAllStations_whenStationsNotExist_thenThrowException() {
        List<Station> stationList = null;
        when(stationRepository.findAll()).thenReturn(stationList);

        assertThrows(StationNotFoundException.class, () -> stationServiceImpl.findAllStations());
    }
}