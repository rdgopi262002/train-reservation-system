package com.gopi.trainservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainRouteResponse {

    private Long id;

    private Integer stopOrder;

    private String stationName;

    private String stationCode;

    private LocalTime arrivalTime;

    private LocalTime departureTime;
}
