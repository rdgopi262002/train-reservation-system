package com.gopi.trainservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Entity
@Table(name="train_routes", uniqueConstraints = {@UniqueConstraint(
        name="uk_train_stop_order",
                    columnNames = {"train_id", "stop_order"}),
    @UniqueConstraint(name="uk_train_station", columnNames = {"train_id","station_id"})

})
@Getter @Setter
public class TrainRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stopOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="train_id", nullable = false)
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="station_id", nullable = false)
    private Station station;

    private LocalTime arrivalTime;

    private LocalTime departureTime;
}


