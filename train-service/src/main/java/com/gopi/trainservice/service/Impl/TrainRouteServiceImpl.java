package com.gopi.trainservice.service.Impl;

import com.gopi.trainservice.dto.request.TrainRouteRequest;
import com.gopi.trainservice.dto.response.TrainRouteResponse;
import com.gopi.trainservice.entity.Station;
import com.gopi.trainservice.entity.Train;
import com.gopi.trainservice.entity.TrainRoute;
import com.gopi.trainservice.exception.custom.DuplicateRouteException;
import com.gopi.trainservice.exception.custom.ResourceNotFoundException;
import com.gopi.trainservice.mapper.TrainRouteMapper;
import com.gopi.trainservice.repository.StationRepository;
import com.gopi.trainservice.repository.TrainRepository;
import com.gopi.trainservice.repository.TrainRouteRepository;
import com.gopi.trainservice.service.Interface.TrainRouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TrainRouteServiceImpl implements TrainRouteService {

    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;
    private final TrainRouteRepository trainRouteRepository;
    private final TrainRouteMapper trainRouteMapper;

    @Override
    @Transactional
    public TrainRouteResponse addRoute(Long trainId, TrainRouteRequest request) {

        log.info("Adding route for trainId={} and stationId={}",
                trainId, request.getStationId());

        Train train = trainRepository.findById(trainId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Train not found: " + trainId));
        log.debug("Train found: id={}, trainNumber={}",
                train.getId(), train.getTrainNo());

        Station station = stationRepository.findById(
                        request.getStationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Station not found: "
                                        + request.getStationId()));

        log.debug("Station found: id={}, stationName={}",
                station.getId(), station.getStationName());

        TrainRoute route =
                trainRouteMapper.toEntity(request);

        route.setTrain(train);
        route.setStation(station);


        TrainRoute savedRoute;

        try {
            log.debug("Saving route: trainId={}, stationId={}, sequence={}",
                    train.getId(),
                    station.getId(),
                    route.getStopOrder());
            savedRoute = trainRouteRepository.saveAndFlush(route);
            log.info("Route added successfully. routeId={}, trainId={}, stationId={}",
                    savedRoute.getId(),
                    trainId,
                    station.getId());
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getRootCause();

            if (cause != null
                    && cause.getMessage()!=null
                    && cause.getMessage().contains("uk_train_station")) {
                log.warn("Duplicate route detected for trainId={} and stationId={}",
                        trainId, request.getStationId());
                throw new DuplicateRouteException("Station already exists in this Route");

            }
            log.error("Database constraint violation", ex);
            throw ex;
        }
            return trainRouteMapper.toResponse(savedRoute);

    }

    @Override
    public List<TrainRouteResponse> getRoutes(Long trainId) {

        List<TrainRoute> routes =
                trainRouteRepository
                        .findByTrainIdOrderByStopOrderAsc(trainId);
        if (routes.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No routes found for train id: " + trainId);
        }

        return routes.stream()
                .map(trainRouteMapper::toResponse)
                .toList();
    }
}
