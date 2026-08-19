package com.gopi.bookingservice.entity;

import com.gopi.bookingservice.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingReference;

    private Long trainId;

    private Long userId;

    private String sourceStation;

    private String destinationStation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
    private Set<BookedSeat> bookedSeats=new HashSet<>();

    private BigDecimal totalFare;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;


    public void addBookedSeat(BookedSeat bookedSeat) {
        bookedSeats.add(bookedSeat);
        bookedSeat.setBooking(this);

    }
}