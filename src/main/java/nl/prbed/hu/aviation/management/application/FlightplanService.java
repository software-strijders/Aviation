package nl.prbed.hu.aviation.management.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.AirportsNotUniqueException;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.airport.SpringAirportRepository;
import nl.prbed.hu.aviation.management.data.flightplan.FlightplanEntity;
import nl.prbed.hu.aviation.management.data.flightplan.SpringFlightplanRepository;
import nl.prbed.hu.aviation.management.domain.Flightplan;
import nl.prbed.hu.aviation.management.domain.factory.FlightplanFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightplanService {
    private static final String ERROR_MSG = "Could not find flightplan with code '%s'";

    private final AirportService airportService;
    private final SpringAirportRepository airportRepository;
    private final SpringFlightplanRepository flightplanRepository;
    private final FlightplanFactory flightplanFactory;

    public Flightplan create(String code, Long duration, String arrival, String destination) {
        if (arrival.equals(destination))
            throw new AirportsNotUniqueException();

        var flightplanEntity = new FlightplanEntity(
                code,
                duration,
                this.airportService.findAirportEntityByCode(arrival),
                this.airportService.findAirportEntityByCode(destination)
        );
        return this.flightplanFactory.from(this.flightplanRepository.save(flightplanEntity));
    }

    public void deleteByCode(String code) {
        var flightplan = this.findFlightplanByCode(code);
        this.flightplanRepository.delete(flightplan);
    }

    public Flightplan findByCode(String code) {
        return this.flightplanFactory.from(this.findFlightplanByCode(code));
    }

    public List<Flightplan> findAll() {
        var entities = this.flightplanRepository.findAll();
        return this.flightplanFactory.from(entities);
    }

    public Flightplan update(String code, Long duration, String arrival, String destination) {
        if (arrival.equals(destination))
            throw new AirportsNotUniqueException();

        var flightplanEntity = this.findFlightplanByCode(code);
        flightplanEntity.setDuration(duration);
        flightplanEntity.setArrival(this.airportService.findAirportEntityByCode(arrival));
        flightplanEntity.setDestination(this.airportService.findAirportEntityByCode(destination));
        return this.flightplanFactory.from(this.flightplanRepository.save(flightplanEntity));
    }

    private FlightplanEntity findFlightplanByCode(String code) {
        return this.flightplanRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code)));
    }
}
