package nl.prbed.hu.aviation.management.data.flight;

import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringFlightRepository extends JpaRepository<FlightEntity, Long> {
    Optional<FlightEntity> findFlightEntityByCode(String code);
    List<FlightEntity> findFlightEntitiesByFlightplanDepartureCode(String code);
    List<FlightEntity> findFlightEntitiesByFlightplanDestinationCode(String code);
    List<FlightEntity> findFlightEntitiesByAircraft(AircraftEntity entity);
}
