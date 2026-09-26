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
    @Column(name = "station_id")
    private Long stationId;
    @Column(name = "station_name")
    private String stationName;
    @Column(name = "station_code")
    private String stationCode;
    @Column(name = "location")
    private String location;

    @OneToOne(mappedBy = "station")
    private MetroCard metroCard2;

    @OneToMany(mappedBy = "station")
    private List<MetroCard> metroCards;

}
