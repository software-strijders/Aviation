package nl.prbed.hu.aviation.management.data.flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringFlightRepository extends JpaRepository<FlightEntity, Long> {
    Optional<FlightEntity> findFlightEntityByCode(String code);
    FlightEntity findFlightEntitiesByFlightplanDepartureCode(String code);
    FlightEntity findFlightEntitiesByFlightplanDestinationCode(String code);
}
