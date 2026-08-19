package com.gopi.trainservice.controller;


import com.gopi.trainservice.dto.request.TrainRouteRequest;
import com.gopi.trainservice.dto.response.TrainRouteResponse;
import com.gopi.trainservice.response.ApiResponse;
import com.gopi.trainservice.service.Interface.TrainRouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/train/route")
@RequiredArgsConstructor
public class TrainRouteController {

    private final TrainRouteService trainRouteService;

    @PostMapping("/{trainId}/routes")
    public ResponseEntity<ApiResponse<TrainRouteResponse>> addRoute(
            @PathVariable("trainId") Long trainId,
            @RequestBody @Valid TrainRouteRequest request) {

        TrainRouteResponse response =
                trainRouteService.addRoute(trainId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Route added successfully",
                        response,
                        null
                ));
    }

    @GetMapping("/{trainId}/routes")
    public ResponseEntity<ApiResponse<List<TrainRouteResponse>>> getRoutes(@PathVariable("trainId") Long trainId) {

        List<TrainRouteResponse> routes =
                trainRouteService.getRoutes(trainId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Routes fetched successfully",
                        routes,
                        null
                )
        );
    }
}