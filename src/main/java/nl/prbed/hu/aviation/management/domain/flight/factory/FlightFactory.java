package nl.prbed.hu.aviation.management.domain.flight.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.airport.AirportEntity;
import nl.prbed.hu.aviation.management.data.flight.FlightEntity;
import nl.prbed.hu.aviation.management.domain.Airport;
import nl.prbed.hu.aviation.management.domain.aircraft.factory.AircraftFactory;
import nl.prbed.hu.aviation.management.domain.factory.FlightSeatFactory;
import nl.prbed.hu.aviation.management.domain.factory.SeatFactory;
import nl.prbed.hu.aviation.management.domain.flight.Flight;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FlightFactory {
    private final AircraftFactory aircraftFactory;
    private final FlightplanFactory flightplanFactory;
    private final FlightSeatFactory flightSeatFactory;

    public Flight from(FlightEntity entity) {
        var seats = entity.getFlightSeats();
        return new Flight(
                entity.getCode(),
                entity.getPriceEconomy(),
                entity.getPriceBusiness(),
                entity.getPriceFirst(),
                null,
                seats != null ? this.flightSeatFactory.from(seats) : null,
                this.aircraftFactory.from(entity.getAircraft()),
                this.flightplanFactory.from(entity.getFlightplan())
        );
    }

    public List<Flight> from(List<FlightEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
