package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class StationRequest {
    private Long stationId;

    private String stationName;

    private String stationCode;

    private String location;

    private List<RouteStationTO> routeStations;
}
