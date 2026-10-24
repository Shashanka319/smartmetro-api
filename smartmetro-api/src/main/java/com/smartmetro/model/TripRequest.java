package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class TripRequest {
    private Long tripId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private double fareAmount;
}
