package com.smartmetro.model;

import com.smartmetro.entity.RouteStation;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RouteTO {
    private Long routeId;

    private String routeName;

    private Set<RouteStationTO> routeStations;
}
