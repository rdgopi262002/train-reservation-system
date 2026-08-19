package com.gopi.trainservice.mapper;



import com.gopi.trainservice.dto.request.StationRequest;
import com.gopi.trainservice.dto.response.StationResponse;
import com.gopi.trainservice.entity.Station;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface StationMapping {

    Station toEntity(StationRequest stationRequest);

    StationResponse toResponse(Station entity);

    void updateEntityFromRequest(StationRequest request, @MappingTarget Station station);



}
