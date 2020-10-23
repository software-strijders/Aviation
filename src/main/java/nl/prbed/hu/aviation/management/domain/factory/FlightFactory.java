package nl.prbed.hu.aviation.management.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.flight.FlightEntity;
import nl.prbed.hu.aviation.management.domain.Flight;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightFactory {
    private final AircraftFactory aircraftFactory;
    private final FlightplanFactory flightplanFactory;

    public Flight from(FlightEntity entity) {
        return new Flight(entity.getCode(), entity.getPriceEconomy(), entity.getPriceBusiness(), entity.getPriceFirst(),
        null, aircraftFactory.from(entity.getAircraft()), flightplanFactory.from(entity.getFlightplan()));
    }
}
