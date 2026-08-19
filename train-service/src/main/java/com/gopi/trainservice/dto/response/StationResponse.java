package com.gopi.trainservice.dto.response;

import com.gopi.trainservice.Enum.RailwayZone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationResponse {

    private Long id;
    private String code;
    private String stationName;
    private String state;
    private RailwayZone zone;

}
