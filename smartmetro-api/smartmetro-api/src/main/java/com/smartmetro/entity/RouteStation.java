package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "route_station")
public class RouteStation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "route_station_id_seq")
    @SequenceGenerator(name = "route_station_id_seq",sequenceName = "route_station_seq",allocationSize=1)
    @Column(name = "route_id")
    private Long route;
    @Column(name = "station_id")
    private Long stationId;
    @Column(name = "sequence_no")
    private Long sequenceNo;

    @ManyToOne
    @JoinColumn(name = "txn_id")
    private TransactionHistory transactionHistory;

}
