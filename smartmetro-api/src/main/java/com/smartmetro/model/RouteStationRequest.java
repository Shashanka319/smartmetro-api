package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RouteStationRequest {
    private Long route;

    private Long stationId;

    private Long sequenceNo;
}
