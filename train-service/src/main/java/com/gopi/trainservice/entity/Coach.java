package com.gopi.trainservice.entity;

import com.gopi.trainservice.Enum.CoachType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Table( name = "coaches",
        uniqueConstraints = {@UniqueConstraint(name = "uk_train_coach", columnNames = {"train_id", "coach_number"})})
@Getter @Setter
@Entity
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private CoachType coachType;

    @Column(name="coach_number", nullable = false)
    private String coachNumber;

    @Column(name="total_seats")
    private Integer totalSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="train_id")
    private  Train train;

    @OneToMany(mappedBy = "coach",fetch = FetchType.LAZY)
    @MapKey(name="seatNumber")
    private Map<Integer,Seat> seats=new LinkedHashMap<>();

    public void addSeat(Seat seat){
        seats.put(seat.getSeatNumber(),seat);
        seat.setCoach(this);
    }

    public void removeSeat(Seat seat){
        seats.remove(seat.getSeatNumber());
        seat.setCoach(null);
    }


}
