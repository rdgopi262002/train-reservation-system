package com.gopi.trainservice.validation.validator;

import com.gopi.trainservice.dto.request.TrainRouteRequest;
import com.gopi.trainservice.validation.annotation.ValidRouteTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrainRouteValidator
        implements ConstraintValidator<
                ValidRouteTime,
                TrainRouteRequest> {

    @Override
    public boolean isValid(
            TrainRouteRequest request,
            ConstraintValidatorContext context) {

        if (request.getArrivalTime() == null
                || request.getDepartureTime() == null) {
            return true;
        }

        return request.getDepartureTime()
                .isAfter(request.getArrivalTime());
    }
}