package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "station_id_seq")
    @SequenceGenerator(name = "station_id_seq",sequenceName = "station_seq",allocationSize=1)
    @Column(name = "STATION_ID")
    private Long stationId;
    @Column(name = "STATION_NAME")
    private String stationName;
    @Column(name = "STATION_CODE")
    private String stationCode;
    @Column(name = "location")
    private String LOCATION ;

    //@OneToOne(mappedBy = "station")
    //private MetroCard metroCard2;

    @OneToMany(mappedBy = "station")
    private List<RouteStation> routeStations;

  //  @OneToMany(mappedBy = "entryStation")
   // private List<Trip> entryTrips;

   // @OneToMany(mappedBy = "exitStation")
    //private List<Trip> exitTrips;

}
