package com.gopi.trainservice.entity;

import com.gopi.trainservice.Enum.CoachType;
import com.gopi.trainservice.Enum.TrainType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name="trains",
        uniqueConstraints = {@UniqueConstraint(name="uk_train_number", columnNames = "trainNo")},
        indexes = @Index(name="indx_train_number", columnList = "trainNo")
)
@Getter @Setter
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String trainName;

    @Column(name ="trainNo", length = 8, nullable = false)
    private String trainNo;

    @Enumerated(EnumType.STRING)
    private TrainType trainType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="source_station_id", nullable=false)
    private Station sourceStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="destination_station_id", nullable=false)
    private Station destinationStation;


    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;

    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY)
    @MapKey(name="coachNumber")
    @OrderBy("id asc")
    private Map<String,Coach> coaches=new LinkedHashMap<>();


    @OneToMany(mappedBy = "train", fetch = FetchType.LAZY,
            orphanRemoval = true,  cascade = CascadeType.ALL)
    @OrderBy("stopOrder ASC ")
    private List<TrainRoute> trainRoutes=new ArrayList<>();


    public void addCoach(Coach coach){
        coaches.put(coach.getCoachNumber(),coach);
        coach.setTrain(this);
    }

    public void removeCoach(Coach coach){
        coaches.remove(coach.getCoachNumber());
        coach.setTrain(null);
    }
}
