package nl.prbed.hu.aviation.management.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.AirportNotFoundException;
import nl.prbed.hu.aviation.management.application.exception.AirportsNotUniqueException;
import nl.prbed.hu.aviation.management.application.exception.FlightplanNotFoundException;
import nl.prbed.hu.aviation.management.data.airport.AirportEntity;
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
    private final SpringFlightplanRepository flightplanRepository;
    private final FlightplanFactory flightplanFactory;
    private final SpringAirportRepository airportRepository;

    public Flightplan create(String code, Long duration, String arrival, String destination) {
        if (arrival.equals(destination))
            throw new AirportsNotUniqueException();

        var flightplanEntity = new FlightplanEntity(
                code,
                duration,
                this.findAirportByCode(arrival),
                this.findAirportByCode(destination)
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
        flightplanEntity.setArrival(this.findAirportByCode(arrival));
        flightplanEntity.setDestination(this.findAirportByCode(destination));
        return this.flightplanFactory.from(this.flightplanRepository.save(flightplanEntity));
    }

    private AirportEntity findAirportByCode(String code) {
        return this.airportRepository.findByCode(code)
                .orElseThrow(() -> new AirportNotFoundException(code));
    }

    private FlightplanEntity findFlightplanByCode(String code) {
        return this.flightplanRepository.findByCode(code)
                .orElseThrow(() -> new FlightplanNotFoundException(code));
    }
}
