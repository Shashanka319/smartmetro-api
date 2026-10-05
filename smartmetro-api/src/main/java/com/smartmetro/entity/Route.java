package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "route_id_seq")
    @SequenceGenerator(name = "route_id_seq",sequenceName = "route_seq",allocationSize=1)
    @Column(name = "ROUTE_ID")
    private Long routeId;

    @Column(name = "ROUTE_NAME")
    private String routeName;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<RouteStation> routeStations;



}
