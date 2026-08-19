package com.gopi.trainservice.service.Impl;

import com.gopi.trainservice.dto.request.TrainRequest;
import com.gopi.trainservice.dto.response.TrainResponse;
import com.gopi.trainservice.entity.Station;
import com.gopi.trainservice.entity.Train;
import com.gopi.trainservice.exception.custom.DuplicateResourceException;
import com.gopi.trainservice.exception.custom.ResourceNotFoundException;
import com.gopi.trainservice.mapper.TrainMapper;
import com.gopi.trainservice.projection.TrainProjection;
import com.gopi.trainservice.repository.StationRepository;
import com.gopi.trainservice.repository.TrainRepository;

import com.gopi.trainservice.repository.TrainRouteRepository;
import com.gopi.trainservice.service.Interface.TrainService;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;
    private final TrainRouteRepository trainRouteRepository;
    private final  StationRepository stationRepository;
    private final  TrainMapper trainMapper;

    @Override
    @Transactional
    public TrainResponse createTrain(TrainRequest request) {

        Station sourceStation = stationRepository.findById(
                        request.getSourceStationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Source station not found: "
                                        + request.getSourceStationId()));

        Station destinationStation = stationRepository.findById(
                        request.getDestinationStationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Destination station not found: "
                                        + request.getDestinationStationId()));


        Train train = trainMapper.toEntity(request);


        train.setSourceStation(sourceStation);
        train.setDestinationStation(destinationStation);

        Train savedTrain;
        try {
            savedTrain = trainRepository.save(train);

        } catch (DataIntegrityViolationException ex) {


            throw new DuplicateResourceException(
                    "Train with this number already exists: "
                            + request.getTrainNo());
        }
        return trainMapper.toResponse(savedTrain);


    }

    @Override
    public TrainResponse getTrainByNumber(String number) {
      TrainProjection response=  trainRepository.findByTrainNo(number).orElseThrow(()->
              new ResourceNotFoundException("Train number not found: " + number));

      return trainMapper.toResponse(response);
    }


    @Override
    public List<TrainResponse> searchTrains(
            String source,
            String destination) {

        Station sourceStation =
                stationRepository.findByStationNameIgnoreCase(source.trim())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Source station not found"));

        Station destinationStation =
                stationRepository.findByStationNameIgnoreCase(destination.trim())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Destination station not found"));

        List<Train> trains =
                trainRouteRepository.findTrainsBetweenStations(
                        sourceStation.getId(),
                        destinationStation.getId());

        if (trains.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No trains found between the given stations");
        }

        return trains.stream()
                .map(trainMapper::toResponse)
                .toList();
    }
}
