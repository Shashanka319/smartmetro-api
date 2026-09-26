package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationTO {
    private Long stationId;

    private String stationName;

    private String stationCode;

    private String location;
}
