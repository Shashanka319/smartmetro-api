package com.smartmetro.model;

import com.smartmetro.entity.RouteStation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StationTO {
    private Long stationId;

    private String stationName;

    private String stationCode;

    private String location;

    private List<RouteStationTO> routeStations;
}
