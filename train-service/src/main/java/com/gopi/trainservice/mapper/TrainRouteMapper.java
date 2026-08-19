package com.gopi.trainservice.mapper;

import com.gopi.trainservice.dto.request.TrainRouteRequest;
import com.gopi.trainservice.dto.response.TrainRouteResponse;
import com.gopi.trainservice.entity.TrainRoute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainRouteMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "train", ignore = true)
    @Mapping(target = "station", ignore = true)
    TrainRoute toEntity(TrainRouteRequest request);

    @Mapping(target = "stationName",
            source = "station.stationName")
    @Mapping(target = "stationCode",
            source = "station.code")
    TrainRouteResponse toResponse(TrainRoute trainRoute);
}
