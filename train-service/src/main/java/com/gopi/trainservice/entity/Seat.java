package com.gopi.trainservice.entity;

import com.gopi.trainservice.Enum.CoachType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name="seats", uniqueConstraints = @UniqueConstraint
        (name="unique_seat_coach", columnNames = {"seat_number", "coach_id"}))
@Entity @Getter @Setter
public class Seat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="seat_number", nullable=false)
    private Integer seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coach_id", nullable=false)
    private Coach coach;

}
