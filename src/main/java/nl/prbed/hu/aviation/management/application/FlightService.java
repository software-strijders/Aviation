package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.application.struct.FlightStruct;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.data.booking.SpringBookingRepository;
import nl.prbed.hu.aviation.management.data.flight.FlightEntity;
import nl.prbed.hu.aviation.management.data.flight.FlightSeatEntity;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightRepository;
import nl.prbed.hu.aviation.management.data.flight.SpringFlightSeatRepository;
import nl.prbed.hu.aviation.management.domain.flight.Flight;
import nl.prbed.hu.aviation.management.domain.flight.factory.FlightFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightService {
    private static final String ERROR_MSG = "Could not find flight with code: '%s'";

    private final SpringBookingRepository bookingRepository;
    private final SpringFlightRepository flightRepository;
    private final SpringFlightSeatRepository flightSeatRepository;

    private final AircraftService aircraftService;
    private final FlightplanService flightplanService;

    private final FlightFactory factory;

    public Flight create(FlightStruct flightStruct) {
        var aircraft = this.aircraftService.findAircraftEntityByCode(flightStruct.aircraftCode);
        var flightplan = this.flightplanService.findFlightplanEntityByCode(flightStruct.flightPlanCode);
        var entity = new FlightEntity(
                flightStruct.code,
                flightStruct.priceEconomy,
                flightStruct.priceBusiness,
                flightStruct.priceFirst,
                flightStruct.departureDateTime,
                null,
                aircraft,
                flightplan,
                null
        );
        var flight = this.flightRepository.save(entity);
        this.saveSeatsForFlight(flight, flight.getAircraft());
        return factory.from(flight);
    }

    public void deleteByCode(String code) {
        var entity = this.flightRepository.findFlightEntityByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code)));
        this.flightSeatRepository.deleteAllByFlight(entity);
        this.bookingRepository.deleteAllByFlight(entity);
        this.flightRepository.delete(entity);
    }

    public Flight update(String oldCode, FlightStruct flightStruct) {
        var entity = flightRepository.findFlightEntityByCode(oldCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, oldCode)));
        entity.setCode(flightStruct.code);
        entity.setPriceFirst(flightStruct.priceFirst);
        entity.setPriceBusiness(flightStruct.priceBusiness);
        entity.setPriceEconomy(flightStruct.priceEconomy);
        entity.setDateTime(flightStruct.departureDateTime);
        entity.setAircraft(this.aircraftService.findAircraftEntityByCode(flightStruct.aircraftCode));
        entity.setFlightplan(this.flightplanService.findFlightplanEntityByCode(flightStruct.flightPlanCode));
        return factory.from(flightRepository.save(entity));
    }

    public Flight findFlightByCode(String code) {
        var entity = this.flightRepository.findFlightEntityByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code)));
        return this.factory.from(entity);
    }

    public List<Flight> findFlightsByDeparture(String code) {
        var entities = this.flightRepository.findFlightEntitiesByFlightplanDepartureCode(code);
        return this.factory.from(entities);
    }

    public List<Flight> findFlightsByDestination(String code) {
        var entities = this.flightRepository.findFlightEntitiesByFlightplanDestinationCode(code);
        return this.factory.from(entities);
    }

    public List<Flight> findAllFlights() {
        var entities = this.flightRepository.findAll();
        return this.factory.from(entities);
    }

    private void saveSeatsForFlight(FlightEntity flight, AircraftEntity aircraft) {
        this.flightSeatRepository.saveAll(
                aircraft.getSeats().stream()
                        .map(seat -> new FlightSeatEntity(seat, null, flight)) // Passenger is set when creating a booking
                        .collect(Collectors.toList())
        );
    }
}
