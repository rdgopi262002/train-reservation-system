package com.gopi.trainservice.projection;

import com.gopi.trainservice.Enum.TrainType;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TrainProjection {
    Long getId();
    String getTrainName();
    String getTrainNo();
    TrainType getTrainType();

    StationProjection getSourceStation();
    StationProjection getDestinationStation();

    LocalTime getStartTime();
    LocalTime getEndTime();

}
