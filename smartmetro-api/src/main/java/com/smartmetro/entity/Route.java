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
    @Column(name = "route_id")
    private Long routeId;
    @Column(name = "route_name")
    private String routeName;

    @OneToMany(mappedBy = "route")
    private Set<RouteStation> routeStations;



}
