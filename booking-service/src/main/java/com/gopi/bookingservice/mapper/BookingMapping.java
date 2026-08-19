package com.gopi.bookingservice.mapper;

import com.gopi.bookingservice.dto.BookingRequest;
import com.gopi.bookingservice.dto.BookingResponse;
import com.gopi.bookingservice.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapping {

    @Mapping(target ="bookingReference", ignore = true)
    @Mapping(target="totalFare", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    Booking toEntity(BookingRequest request);

    BookingResponse toDto(Booking entity);
}
