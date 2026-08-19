package com.gopi.trainservice.dto.response;

import com.gopi.trainservice.Enum.TrainType;
import com.gopi.trainservice.entity.Train;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
public class TrainResponse {
    private Long id;
    private String trainName;
    private String trainNo;
    private TrainType trainType;

    private String sourceStationName;
    private String destinationStationName;

    private LocalTime startTime;
    private LocalTime endTime;


}
