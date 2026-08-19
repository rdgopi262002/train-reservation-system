package com.gopi.bookingservice.service;

import com.gopi.bookingservice.adapter.TrainAdapter;
import com.gopi.bookingservice.dto.BookingRequest;
import com.gopi.bookingservice.dto.BookingResponse;
import com.gopi.bookingservice.dto.TrainResponse;
import com.gopi.bookingservice.entity.Booking;
import com.gopi.bookingservice.mapper.BookingMapping;
import com.gopi.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapping bookingMapping;
    private final TrainAdapter trainAdapter;

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {

        TrainResponse train=trainAdapter.getTrainByNumber(request.trainNumber());


        return null;
    }

    @Override
    public BookingResponse getBooking(Long id) {
        return null;
    }

    @Override
    public Page<BookingResponse> getBookingsByUser(Long userId) {
        return null;
    }

    @Override
    public void cancelBooking(Long id) {

    }
}
