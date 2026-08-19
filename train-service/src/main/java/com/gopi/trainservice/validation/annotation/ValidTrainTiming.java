package com.gopi.trainservice.validation.annotation;

import com.gopi.trainservice.validation.validator.TrainTimingValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TrainTimingValidator.class})
@Documented
public @interface ValidTrainTiming {


    String message() default
            "End time must be after start time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
