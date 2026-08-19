package com.gopi.trainservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopi.trainservice.Enum.TrainType;
import com.gopi.trainservice.dto.request.TrainRequest;
import com.gopi.trainservice.dto.response.TrainResponse;

import com.gopi.trainservice.service.Interface.TrainService;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainController.class)
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TrainService trainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateTrainSuccessfully() throws Exception {

        // =====================
        // REQUEST
        // =====================
        TrainRequest request = new TrainRequest();
        request.setTrainName("Chennai Express");
        request.setTrainNo("12345");
        request.setTrainType(TrainType.EXPRESS);
        request.setSourceStationId(1L);
        request.setDestinationStationId(2L);
        request.setStartTime(LocalTime.of(10, 0));
        request.setEndTime(LocalTime.of(20, 0));

        // =====================
        // RESPONSE
        // =====================
        TrainResponse response = new TrainResponse();
        response.setId(100L);
        response.setTrainName("Chennai Express");
        response.setTrainNo("12345");
        response.setTrainType(TrainType.EXPRESS);
        response.setSourceStationName("Chennai");
        response.setDestinationStationName("Delhi");
        response.setStartTime(request.getStartTime());
        response.setEndTime(request.getEndTime());

        // =====================
        // MOCK SERVICE (MOST IMPORTANT FIX)
        // =====================
        when(trainService.createTrain(any(TrainRequest.class)))
                .thenReturn(response);

        // =====================
        // TEST CALL
        // =====================
        mockMvc.perform(post("/train")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect( jsonPath("$.success").value(true))
                .andExpect( jsonPath("$.message").value("Train created successfully"))

                // data checks
                .andExpect( jsonPath("$.data.id").value(100))
                .andExpect( jsonPath("$.data.trainName").value("Chennai Express"))
                .andExpect( jsonPath("$.data.trainNo").value("12345"))
                .andExpect( jsonPath("$.data.trainType").value("EXPRESS"))
                .andExpect( jsonPath("$.data.sourceStationName").value("Chennai"))
                .andExpect( jsonPath("$.data.destinationStationName").value("Delhi"));
    }
}
