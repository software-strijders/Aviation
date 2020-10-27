package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.AirportsNotUniqueException;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
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
    private static final String ERROR_MSG = "Could not find flightplan with code: '%s'";

    private final SpringFlightplanRepository flightplanRepository;

    private final AirportService airportService;

    private final FlightplanFactory flightplanFactory;

    public Flightplan create(String code, Long duration, String departure, String destination) {
        if (departure.equals(destination))
            throw new AirportsNotUniqueException();

        var entity = new FlightplanEntity(
                code,
                duration,
                this.airportService.findAirportEntityByCode(departure),
                this.airportService.findAirportEntityByCode(destination)
        );
        return this.flightplanFactory.from(this.flightplanRepository.save(entity));
    }

    public void deleteByCode(String code) {
        var entity = this.findFlightplanEntityByCode(code);
        this.flightplanRepository.delete(entity);
    }

    public Flightplan findFlightplanByCode(String code) {
        return this.flightplanFactory.from(this.findFlightplanEntityByCode(code));
    }

    public List<Flightplan> findAll() {
        var entities = this.flightplanRepository.findAll();
        return this.flightplanFactory.from(entities);
    }

    public Flightplan update(String oldCode, String newCode, Long duration, String departure, String destination) {
        if (departure.equals(destination))
            throw new AirportsNotUniqueException();

        var entity = this.findFlightplanEntityByCode(oldCode);
        entity.setCode(newCode);
        entity.setDuration(duration);
        entity.setDeparture(this.airportService.findAirportEntityByCode(departure));
        entity.setDestination(this.airportService.findAirportEntityByCode(destination));
        return this.flightplanFactory.from(this.flightplanRepository.save(entity));
    }

    public FlightplanEntity findFlightplanEntityByCode(String code) {
        return this.flightplanRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code)));
    }
}
