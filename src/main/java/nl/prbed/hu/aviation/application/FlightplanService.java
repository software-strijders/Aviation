package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.exception.AirportNotFoundException;
import nl.prbed.hu.aviation.application.exception.AirportsNotUniqueException;
import nl.prbed.hu.aviation.application.exception.FlightplanNotFoundException;
import nl.prbed.hu.aviation.data.airport.SpringAirportRepository;
import nl.prbed.hu.aviation.data.flightplan.FlightplanEntity;
import nl.prbed.hu.aviation.data.flightplan.SpringFlightplanRepository;
import nl.prbed.hu.aviation.domain.Flightplan;
import nl.prbed.hu.aviation.domain.factory.FlightplanFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightplanService {
    private final SpringFlightplanRepository flightplanRepository;
    private final FlightplanFactory flightplanFactory;
    private final SpringAirportRepository airportRepository;

    public Flightplan create(String code, Long duration, String arrival, String destination) {
        if (arrival.equals(destination)) {
            throw new AirportsNotUniqueException();
        }

        var flightplanEntity = new FlightplanEntity(
                null,
                code,
                duration,
                this.airportRepository.findByCode(arrival)
                        .orElseThrow(() -> new AirportNotFoundException(arrival)),
                this.airportRepository.findByCode(destination)
                        .orElseThrow(() -> new AirportNotFoundException(destination))
        );

        return this.flightplanFactory.from(this.flightplanRepository.save(flightplanEntity));
    }

    public Flightplan update(String code, Long duration, String arrival, String destination) {
        if (arrival.equals(destination)) {
            throw new AirportsNotUniqueException();
        }

        var flightplanEntity = flightplanRepository.findByCode(code)
                .orElseThrow(() -> new FlightplanNotFoundException(code));

        flightplanEntity.setDuration(duration);
        flightplanEntity.setArrival(this.airportRepository.findByCode(arrival)
                .orElseThrow(() -> new AirportNotFoundException(arrival)));
        flightplanEntity.setDestination(this.airportRepository.findByCode(destination)
                .orElseThrow(() -> new AirportNotFoundException(destination)));

        return this.flightplanFactory.from(this.flightplanRepository.save(flightplanEntity));
    }

    public Flightplan findByCode(String code) {
        return this.flightplanFactory.from(this.flightplanRepository.findByCode(code)
                .orElseThrow(() -> new FlightplanNotFoundException(code)));
    }

    public List<Flightplan> findAll() {
        var entities = this.flightplanRepository.findAll();
        return this.flightplanFactory.from(entities);
    }

    public void deleteByCode(String code) {
        this.flightplanRepository.findByCode(code)
                .orElseThrow(() -> new FlightplanNotFoundException(code));
        this.flightplanRepository.deleteFlightplanEntityByCode(code);
    }
}
