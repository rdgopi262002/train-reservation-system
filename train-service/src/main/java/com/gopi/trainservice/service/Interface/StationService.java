package com.gopi.trainservice.service.Interface;


import com.gopi.trainservice.dto.request.StationRequest;
import com.gopi.trainservice.dto.response.StationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StationService {



        StationResponse createStation(StationRequest request);

        StationResponse getStationById(Long id);

        Page<StationResponse> getAllStations(Pageable pageable);

        StationResponse updateStation(Long id,
                                      StationRequest request);

        void deleteStation(Long id);

        StationResponse getStationByCode(String code);
    }

