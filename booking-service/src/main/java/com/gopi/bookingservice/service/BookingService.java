package com.gopi.bookingservice.service;

import com.gopi.bookingservice.dto.BookingRequest;
import com.gopi.bookingservice.dto.BookingResponse;
import org.springframework.data.domain.Page;

public interface BookingService {


    BookingResponse createBooking(
            BookingRequest request);

    BookingResponse getBooking(
            Long id);

    Page<BookingResponse> getBookingsByUser(
            Long userId);

    void cancelBooking(Long id);
}
