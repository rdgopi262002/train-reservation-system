package com.gopi.bookingservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookingRequest(
        @NotNull(message = "Train id is required")
        Long trainId,

        @NotNull(message = "Train number is required")
        Long trainNumber,
        @NotNull(message = "User id is required")
        Long userId,
        @NotBlank(message = "Source station is required")
        String sourceStation,
        @NotBlank(message = "Destination station is required")
        String destinationStation,

        @NotNull(message = "Seat count is required")
        @Min(value = 1, message = "Seat count must be at least 1")
        Integer seatCount
) {
}
