package com.gopi.trainservice.validation.validator;

import com.gopi.trainservice.dto.request.TrainRequest;
import com.gopi.trainservice.validation.annotation.ValidTrainTiming;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrainTimingValidator implements
        ConstraintValidator<ValidTrainTiming, TrainRequest> {

    @Override
    public boolean isValid(TrainRequest value, ConstraintValidatorContext context) {

        if (value.getStartTime() == null ||
                value.getEndTime() == null) {
            return true; // let @NotNull handle this
        }

        return value.getEndTime()
                .isAfter(value.getStartTime());
    }
}
