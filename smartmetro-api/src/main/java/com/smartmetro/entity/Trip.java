package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trip_id_seq")
    @SequenceGenerator(name = "trip_id_seq", sequenceName = "trip_seq", allocationSize = 1)
    @Column(name = "TRIP_ID")
    private Long tripId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private MetroCard metroCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTRY_STATION_ID")
    private Station entryStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXIT_STATION_ID")
    private Station exitStation;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "END_TIME")
    private LocalDateTime endTime;

    @Column(name = "FARE_AMOUNT")
    private Double fareAmount;

}