package com.smartmetro.model;

import java.util.List;

public class StationRequest {
    private Long stationId;

    private String stationName;

    private String stationCode;

    private String location;

    private List<RouteStationTO> routeStations;
}
