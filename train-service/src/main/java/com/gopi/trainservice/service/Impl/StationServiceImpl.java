package com.gopi.trainservice.service.Impl;

import com.gopi.trainservice.dto.request.StationRequest;
import com.gopi.trainservice.dto.response.StationResponse;
import com.gopi.trainservice.entity.Station;
import com.gopi.trainservice.exception.custom.DuplicateResourceException;
import com.gopi.trainservice.exception.custom.ResourceNotFoundException;


import com.gopi.trainservice.mapper.StationMapping;
import com.gopi.trainservice.repository.StationRepository;
import com.gopi.trainservice.service.Interface.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {


     private final StationRepository stationRepository;
     private final StationMapping stationMapping;

    @Override
    @Transactional
    public StationResponse createStation(StationRequest request) {

        if (stationRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException(
                    "Station with this code already exists: " + request.getCode());
        }
            Station station = stationMapping.toEntity(request);
            return stationMapping.toResponse(stationRepository.save(station));

        }

    @Override
    @Transactional(readOnly = true)
    public StationResponse getStationById(Long id) {

     Station station = stationRepository.findById(id).orElseThrow(
             ()-> new ResourceNotFoundException("Station with this id does not exists :" + id));
     return stationMapping.toResponse(station);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StationResponse> getAllStations(Pageable pageable) {

        return stationRepository.findAll(pageable)
                .map(stationMapping::toResponse);
    }

    @Override
    @Transactional
    public StationResponse updateStation(Long id, StationRequest request) {
        Station station = stationRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Station with this id does not exists :" + id));

        if (!station.getCode().equals(request.getCode())
                && stationRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException(
                    "Station with this code already exists: " + request.getCode());
        }

        stationMapping.updateEntityFromRequest(request,station);
        return stationMapping.toResponse(stationRepository.save(station));
    }

    @Override
    @Transactional
    public void deleteStation(Long id) {

        Station station = stationRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Can't delete a station that doesn't exists :" + id));
        stationRepository.delete(station);
    }

    @Override
    @Transactional(readOnly = true)
    public StationResponse getStationByCode(String code) {
        Station station = stationRepository.findByCode(code).orElseThrow(
                ()-> new ResourceNotFoundException("Station with this code does not exist :" + code));
        return stationMapping.toResponse(station);
    }
}

