package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.flight.FlightEntity;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightRepository;
import nl.prbed.hu.aviation.management.domain.Flight;
import nl.prbed.hu.aviation.management.domain.Flightplan;
import nl.prbed.hu.aviation.management.domain.factory.FlightFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final SpringFlightRepository flightRepository;
    private final AircraftService aircraftService;
    private final FlightplanService flightplanService;
    private final FlightFactory flightFactory;

    public Flight create(String code, double priceFirst, double priceBusiness, double priceEconomy,
                         String aircraftCode, String flightPlanCode) {
        var aircraft = this.aircraftService.findAircraftEntityByCode(aircraftCode);
        var flightplan = this.flightplanService.findFlightplanEntityByCode(flightPlanCode);
        var entity = new FlightEntity(code, priceEconomy, priceBusiness, priceFirst, aircraft, flightplan);

        return flightFactory.from(flightRepository.save(entity));
    }

}
