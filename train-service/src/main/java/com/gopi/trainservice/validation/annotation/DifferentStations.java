package com.gopi.trainservice.validation.annotation;


import com.gopi.trainservice.validation.validator.DifferentStationsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DifferentStationsValidator.class)
@Documented
public @interface DifferentStations {

    String message() default "Source station and destination station cannot be the same";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
