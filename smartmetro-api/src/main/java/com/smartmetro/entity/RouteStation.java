package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "route_station")
@IdClass(RouteStation.RouteStationPK.class)
public class RouteStation {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ROUTE_ID")
    private Route route;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATION_ID", referencedColumnName = "STATION_ID")
    private Station station;

    @Column(name = "SEQUENCE_NO")
    private Long sequenceNo;

    // Embedded composite key matching (route_id, station_id)
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class RouteStationPK implements Serializable {
        private Long route;
        private Long station;
    }
}