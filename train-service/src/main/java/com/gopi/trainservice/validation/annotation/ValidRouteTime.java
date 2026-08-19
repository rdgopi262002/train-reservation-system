package com.gopi.trainservice.validation.annotation;

import com.gopi.trainservice.validation.validator.TrainRouteValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TrainRouteValidator.class)
public @interface ValidRouteTime {

    String message() default
            "Departure time must be after arrival time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}