package com.gopi.trainservice.dto.request;

import com.gopi.trainservice.Enum.TrainType;
import com.gopi.trainservice.validation.annotation.DifferentStations;
import com.gopi.trainservice.validation.annotation.ValidTrainTiming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
@DifferentStations
@ValidTrainTiming
public class TrainRequest {

    @NotBlank
    @Pattern(  regexp = "^[A-Za-z ]+$",
            message = "Train name can contain only letters and spaces")
    private String trainName;

    @NotBlank
    @Pattern(
            regexp = "^\\d{5}$",
            message = "Train number must contain exactly 5 digits"
    )
   private String trainNo;

    @NotNull(message = "Train type is required")
    private TrainType trainType;

    @NotNull(message = "Source station is required")
    private Long sourceStationId;

    @NotNull(message = "Destination station is required")
    private Long destinationStationId;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;
}
