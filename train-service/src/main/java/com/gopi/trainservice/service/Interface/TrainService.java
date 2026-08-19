package com.gopi.trainservice.service.Interface;

import com.gopi.trainservice.dto.request.TrainRequest;
import com.gopi.trainservice.dto.response.TrainResponse;

import java.util.List;

public interface TrainService {
    public TrainResponse createTrain(TrainRequest request) ;

    public TrainResponse getTrainByNumber(String number) ;

    public List<TrainResponse> searchTrains(String source, String destination) ;
}
