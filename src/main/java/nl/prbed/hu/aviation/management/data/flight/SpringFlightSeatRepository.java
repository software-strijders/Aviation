package nl.prbed.hu.aviation.management.data.flight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringFlightSeatRepository extends JpaRepository<FlightSeatEntity, Long> {
    void deleteAllByFlight(FlightEntity flightEntity);
}
