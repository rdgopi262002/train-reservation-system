package com.gopi.trainservice.controller;

import com.gopi.trainservice.dto.request.TrainRequest;
import com.gopi.trainservice.dto.response.TrainResponse;
import com.gopi.trainservice.response.ApiResponse;
import com.gopi.trainservice.service.Interface.TrainService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/train")
public class TrainController {

    private final TrainService trainService;

    @PostMapping
    public ResponseEntity<ApiResponse<TrainResponse>> createTrain(
            @Valid @RequestBody TrainRequest request) {

        TrainResponse response = trainService.createTrain(request);

        ApiResponse<TrainResponse> apiResponse =
                new ApiResponse<>(
                        true, "Train created successfully", response, null
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @GetMapping("/{number}")
    public ResponseEntity<ApiResponse<TrainResponse>> getTrainByNumber(@PathVariable("number") String number){
        TrainResponse response=trainService.getTrainByNumber(number);
        ApiResponse<TrainResponse> apiResponse=new ApiResponse<>(
                true, "Train fetched successfully",response,null);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<TrainResponse>>> searchTrains(
            @RequestParam("source") String source,
            @RequestParam("destination") String destination) {

        List<TrainResponse> trains =
                trainService.searchTrains(
                        source,
                        destination);

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Trains fetched successfully",
                trains,
                null));

    }
}
