package com.gopi.trainservice.controller;




import com.gopi.trainservice.dto.request.StationRequest;
import com.gopi.trainservice.dto.response.StationResponse;
import com.gopi.trainservice.response.ApiResponse;
import com.gopi.trainservice.service.Interface.StationService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/train/station")
public class StationController  {

    private final StationService stationService;

    @PostMapping
    public ResponseEntity<ApiResponse<StationResponse>> createStation(@RequestBody StationRequest stationRequest) {
        StationResponse response = stationService.createStation(stationRequest);
        ApiResponse<StationResponse> apiResponse =new ApiResponse<>
                (true,"Station Created Successfully", response, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @GetMapping("/code/{code}")
    public ResponseEntity<StationResponse> getStationByCode(@PathVariable("code") String code) {
       StationResponse response = stationService.getStationByCode(code.toUpperCase());
       return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StationResponse>> getStationById(@PathVariable("id") Long id) {
        StationResponse response = stationService.getStationById(id);
        ApiResponse<StationResponse> apiResponse =new ApiResponse<>
                (true,"Station fetched Successfully", response, null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<StationResponse> updateStation(@PathVariable("id") Long id, @RequestBody StationRequest stationRequest) {
        StationResponse response = stationService.updateStation(id, stationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable("id") Long id) {
       stationService.deleteStation(id);
       return ResponseEntity.noContent().build();
    }


}

