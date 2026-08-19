package com.gopi.bookingservice.adapter;

import com.gopi.bookingservice.client.TrainClient;
import com.gopi.bookingservice.dto.ApiResponse;
import com.gopi.bookingservice.dto.TrainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class TrainAdapter {

    private final TrainClient trainClient;

    public TrainResponse getTrainByNumber(Long trainId) {

        ApiResponse<TrainResponse> response = trainClient.getTrainById(trainId);

        return response.getData();
    }

}
