package com.gopi.trainservice.dto.request;

import com.gopi.trainservice.Enum.RailwayZone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationRequest {

   private String code;
   private String stationName;
   private String state;
   private RailwayZone zone;
}
