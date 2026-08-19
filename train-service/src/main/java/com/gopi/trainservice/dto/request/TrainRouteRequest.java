package com.gopi.trainservice.dto.request;


import com.gopi.trainservice.validation.annotation.ValidRouteTime;
import com.gopi.trainservice.validation.annotation.ValidTrainTiming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ValidRouteTime
public class TrainRouteRequest {

    @NotNull
    private Long stationId;

    @NotNull
    @Min(1)
    private Integer stopOrder;

    @NotNull
    private LocalTime arrivalTime;

    @NotNull
    private LocalTime departureTime;
}