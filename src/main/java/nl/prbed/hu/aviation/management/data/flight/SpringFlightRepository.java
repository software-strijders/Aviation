package nl.prbed.hu.aviation.management.data.flight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringFlightRepository extends JpaRepository<FlightEntity, Long> {
    FlightEntity findFlightEntityByCode(String code);
    FlightEntity findFlightEntitiesByFlightplanArrivalCode(String code);
    FlightEntity findFlightEntitiesByFlightplanDestinationCode(String code);
}
