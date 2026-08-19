package com.gopi.bookingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookedSeat {

    @Id
    @GeneratedValue
    private Long id;

    private Long seatId;

    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
