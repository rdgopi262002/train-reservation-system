package com.gopi.trainservice.service.Interface;

import com.gopi.trainservice.dto.request.TrainRouteRequest;
import com.gopi.trainservice.dto.response.TrainRouteResponse;

import java.util.List;

public interface TrainRouteService {

    TrainRouteResponse addRoute(
            Long trainId,
            TrainRouteRequest request);


    List<TrainRouteResponse> getRoutes(Long trainId);
}
