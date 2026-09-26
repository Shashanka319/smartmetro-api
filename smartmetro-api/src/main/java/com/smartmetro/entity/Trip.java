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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "trip_id_seq")
    @SequenceGenerator(name = "trip_id_seq",sequenceName = "trip_seq",allocationSize=1)
    @Column(name = "trip_id")
    private Long tripId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private MetroCard metroCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_station_id")
    private Station entryStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exit_station_id")
    private Station exitStation;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "fare_amount")
    private double fareAmount;

    @ManyToOne()
    @JoinColumn(name = "station_id")
    private Station station;

}