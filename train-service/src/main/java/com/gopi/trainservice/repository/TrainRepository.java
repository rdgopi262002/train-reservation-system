package com.gopi.trainservice.repository;

import com.gopi.trainservice.dto.response.TrainResponse;
import com.gopi.trainservice.entity.Train;
import com.gopi.trainservice.projection.TrainProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train,Long> {


    Optional<TrainProjection> findByTrainNo(String name);


}
