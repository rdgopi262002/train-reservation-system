package com.gopi.trainservice.validation.validator;

import com.gopi.trainservice.dto.request.TrainRequest;
import com.gopi.trainservice.validation.annotation.DifferentStations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DifferentStationsValidator implements
        ConstraintValidator<DifferentStations,TrainRequest> {
    @Override
    public boolean isValid(TrainRequest value, ConstraintValidatorContext context) {



        if (value.getSourceStationId() == null || value.getDestinationStationId() == null) {
            return true;
        }
        if (value.getSourceStationId()
                .equals(value.getDestinationStationId())) {

            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(
                            "Destination station cannot be same as source station")
                    .addPropertyNode("destinationStationId")
                    .addConstraintViolation();

            return false;
        }

        return true;
}}