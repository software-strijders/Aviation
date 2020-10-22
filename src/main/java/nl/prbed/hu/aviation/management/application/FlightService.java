package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightRepository;
import nl.prbed.hu.aviation.management.domain.Flight;
import nl.prbed.hu.aviation.management.domain.Flightplan;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final SpringFlightRepository flightRepository;
    private final AircraftService aircraftService;
    private final FlightplanService flightplanService;

    public Flight create(String code, double priceFirst, double priceBusiness, double priceEconomy,
                         String aircraftCode, String flightPlanCode) {
        var aircraft = this.aircraftService.findByCode(aircraftCode);
        var flightplan = this.flightplanService.findByCode(flightPlanCode);

        return new Flight(code, priceEconomy, priceBusiness, priceFirst, null, aircraft, flightplan);
    }

}
