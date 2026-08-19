package com.gopi.trainservice.repository;

import com.gopi.trainservice.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station,Long> {

    Optional<Station> findByCode(String code);

    boolean existsByCode(String code);

    Optional<Station> findByStationNameIgnoreCase(String stationName);

}
