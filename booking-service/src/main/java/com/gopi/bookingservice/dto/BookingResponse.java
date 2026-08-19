package com.gopi.bookingservice.dto;

public record BookingResponse(   Long bookingId,
                                 String bookingReference,
                                 String status,
                                 Integer seatCount,
                                 Double totalFare) {
}
