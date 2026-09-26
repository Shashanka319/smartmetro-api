package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TripTO {
    private Long tripId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private double fareAmount;
}
