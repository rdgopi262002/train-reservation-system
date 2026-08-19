package com.gopi.bookingservice.client;

import com.gopi.bookingservice.dto.ApiResponse;
import com.gopi.bookingservice.dto.TrainResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="TRAIN-SERVICE")
public interface TrainClient {

    @GetMapping("/train/{number}")
    ApiResponse<TrainResponse> getTrainById(@PathVariable Long id);
}


