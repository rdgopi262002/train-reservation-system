package com.gopi.trainservice.entity;



import com.gopi.trainservice.Enum.RailwayZone;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="stations",
        indexes = {@Index(name="ind_code", columnList = "station_code"),
                   @Index(name="ind_station_name", columnList = "station_name")}
)
@Getter
@Setter
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="station_code", unique=true, nullable=false, length=10 )
    private String code;

    @Column(name="station_name", nullable=false)
    private String stationName;

    @Column(nullable=false)
    private String state;


    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private RailwayZone zone;
}
