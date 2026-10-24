package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
@Getter
@Setter
@ToString
public class RouteRequest {
    private Long routeId;

    private String routeName;

    private Set<RouteStationTO> routeStations;
}
