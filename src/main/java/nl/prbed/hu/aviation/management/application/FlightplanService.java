package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.AirportsNotUniqueException;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.airport.SpringAirportRepository;
import nl.prbed.hu.aviation.management.data.flightplan.FlightplanEntity;
import nl.prbed.hu.aviation.management.data.flightplan.SpringFlightplanRepository;
import nl.prbed.hu.aviation.management.domain.flight.Flightplan;
import nl.prbed.hu.aviation.management.domain.flight.factory.FlightplanFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightplanService {
    private static final String ERROR_MSG = "Could not find flightplan with code '%s'";

    private final AirportService airportService;
    private final SpringAirportRepository airportRepository;
    private final SpringFlightplanRepository flightplanRepository;
    private final FlightplanFactory flightplanFactory;

    public Flightplan create(String code, Long duration, String departure, String destination) {
        if (departure.equals(destination))
            throw new AirportsNotUniqueException();

        var flightplanEntity = new FlightplanEntity(
                code,
                duration,
                this.airportService.findAirportEntityByCode(departure),
                this.airportService.findAirportEntityByCode(destination)
        );
        return this.flightplanFactory.from(this.flightplanRepository.save(flightplanEntity));
    }

    public void deleteByCode(String code) {
        var flightplan = this.findFlightplanEntityByCode(code);
        this.flightplanRepository.delete(flightplan);
    }

    public Flightplan findFlightplanByCode(String code) {
        return this.flightplanFactory.from(this.findFlightplanEntityByCode(code));
    }

    public List<Flightplan> findAll() {
        var entities = this.flightplanRepository.findAll();
        return this.flightplanFactory.from(entities);
    }

    public Flightplan update(String oldCode, String code, Long duration, String departure, String destination) {
        if (departure.equals(destination))
            throw new AirportsNotUniqueException();

        var flightplanEntity = this.findFlightplanEntityByCode(oldCode);
        flightplanEntity.setCode(code);
        flightplanEntity.setDuration(duration);
        flightplanEntity.setDeparture(this.airportService.findAirportEntityByCode(departure));
        flightplanEntity.setDestination(this.airportService.findAirportEntityByCode(destination));
        return this.flightplanFactory.from(this.flightplanRepository.save(flightplanEntity));
    }

    public FlightplanEntity findFlightplanEntityByCode(String code) {
        return this.flightplanRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code)));
    }
}
