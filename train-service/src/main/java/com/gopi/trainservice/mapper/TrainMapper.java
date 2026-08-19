package com.gopi.trainservice.mapper;

import com.gopi.trainservice.dto.request.TrainRequest;
import com.gopi.trainservice.dto.response.TrainResponse;
import com.gopi.trainservice.entity.Train;
import com.gopi.trainservice.projection.TrainProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sourceStation", ignore = true)
    @Mapping(target = "destinationStation", ignore = true)
    @Mapping(target = "coaches", ignore = true)
    @Mapping(target = "trainRoutes", ignore = true)
    Train toEntity(TrainRequest request);

    @Mapping(target = "sourceStationName", source = "sourceStation.stationName")
    @Mapping(target = "destinationStationName", source = "destinationStation.stationName")
    TrainResponse toResponse(Train train);

    @Mapping(target = "sourceStationName",
            source = "sourceStation.stationName")
    @Mapping(target = "destinationStationName",
            source = "destinationStation.stationName")
    TrainResponse toResponse(TrainProjection projection);
}
