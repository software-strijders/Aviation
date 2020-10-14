package nl.prbed.hu.aviation.data.flightplan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringFlightplanRepository extends JpaRepository<FlightplanEntity, Long> {
    Optional<FlightplanEntity> findByCode(String code);
}
