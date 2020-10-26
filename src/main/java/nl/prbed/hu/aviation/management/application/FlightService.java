package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.flight.FlightEntity;
import nl.prbed.hu.aviation.management.data.flight.FlightSeatEntity;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightRepository;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightSeatRepository;
import nl.prbed.hu.aviation.management.domain.flight.Flight;
import nl.prbed.hu.aviation.management.domain.flight.factory.FlightFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightService {
    private static final String ERROR_MSG = "Could not find flight with code '%s'";

    private final FlightFactory flightFactory;

    private final AircraftService aircraftService;
    private final FlightplanService flightplanService;

    private final SpringFlightRepository flightRepository;
    private final SpringFlightSeatRepository flightSeatRepository;

    public Flight create(
            String code,
            double priceFirst,
            double priceBusiness,
            double priceEconomy,
            String aircraftCode,
            String flightPlanCode
    ) {
        var aircraft = this.aircraftService.findAircraftEntityByCode(aircraftCode);
        var flightplan = this.flightplanService.findFlightplanEntityByCode(flightPlanCode);
        var entity = new FlightEntity(code, priceEconomy, priceBusiness, priceFirst, null, aircraft, flightplan, null);
        var flight = this.flightRepository.save(entity);
        this.saveSeatsForFlight(flight, flight.getAircraft());
        return flightFactory.from(flight);
    }

    private void saveSeatsForFlight(FlightEntity flight, AircraftEntity aircraft) {
        this.flightSeatRepository.saveAll(
                aircraft.getSeats().stream()
                        .map(seat -> new FlightSeatEntity(seat, null, flight)) // Passenger is set when creating a booking
                        .collect(Collectors.toList())
        );
    }

    public Flight update (
            String oldCode,
            String newCode,
            double priceFirst,
            double priceBusiness,
            double priceEconomy,
            String aircraftCode,
            String flightPlanCode
    ) {
        System.out.println(oldCode + newCode);


        var entity = flightRepository.findFlightEntityByCode(oldCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, oldCode)));

        entity.setCode(newCode);
        entity.setPriceFirst(priceFirst);
        entity.setPriceBusiness(priceBusiness);
        entity.setPriceEconomy(priceEconomy);
        entity.setAircraft(this.aircraftService.findAircraftEntityByCode(aircraftCode));
        entity.setFlightplan(this.flightplanService.findFlightplanEntityByCode(flightPlanCode));
        return flightFactory.from(flightRepository.save(entity));
    }

    public void deleteByCode(String code) {
        this.flightRepository.delete(this.flightRepository.findFlightEntityByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code))));
    }
}
