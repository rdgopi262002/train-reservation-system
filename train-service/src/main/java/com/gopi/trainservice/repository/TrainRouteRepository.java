package com.gopi.trainservice.repository;

import com.gopi.trainservice.entity.Train;
import com.gopi.trainservice.entity.TrainRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainRouteRepository extends JpaRepository<TrainRoute, Long> {
    List<TrainRoute> findByTrainIdOrderByStopOrderAsc(Long trainId);

    @Query("""
               SELECT t
               FROM Train t
               JOIN TrainRoute src ON t.id = src.train.id
               JOIN TrainRoute dest ON t.id = dest.train.id
               WHERE src.station.id = :sourceId
               AND dest.station.id = :destinationId
               AND src.stopOrder < dest.stopOrder
           """)
    List<Train> findTrainsBetweenStations(
            @Param("sourceId")Long sourceId,
            @Param("destinationId")Long destinationId);


}
