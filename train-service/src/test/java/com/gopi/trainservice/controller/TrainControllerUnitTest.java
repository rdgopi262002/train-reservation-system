package com.gopi.trainservice.controller;

import com.gopi.trainservice.dto.request.TrainRequest;
import com.gopi.trainservice.dto.response.TrainResponse;
import com.gopi.trainservice.service.Interface.TrainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class TrainControllerUnitTest {

    @Mock
    private TrainService trainService;

    @InjectMocks
    private TrainController trainController;

    @Test
    public void shouldCreateTrainSuccessfully() {

        TrainRequest trainRequest = new TrainRequest();
        trainRequest.setTrainName("Chennai");
        trainRequest.setTrainNo("123456");


        TrainResponse trainResponse = new TrainResponse();

        assertEquals()
    }
}
