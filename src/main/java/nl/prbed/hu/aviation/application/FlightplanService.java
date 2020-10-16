package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.exception.FlightplanNotFoundException;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.data.flightplan.SpringFlightplanRepository;
import nl.prbed.hu.aviation.data.flightplan.factory.FlightPlanEntityFactory;
import nl.prbed.hu.aviation.domain.Flightplan;
import nl.prbed.hu.aviation.domain.factory.FlightplanFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightplanService {
    private final SpringFlightplanRepository flightplanRepository;
    private final FlightPlanEntityFactory flightplanEntityFactory;
    private final FlightplanFactory flightplanFactory;

    //TODO: fix destination and arrival
    public Flightplan create(Long duration, String code) {
        AirportEntity arrival = null;
        AirportEntity destination = null;
        var entity = flightplanEntityFactory.create(code, duration, arrival, destination);

        return this.flightplanFactory.from(this.flightplanRepository.save(entity));
    }

    public Flightplan findByCode(String code) {
        return this.flightplanFactory.from(this.flightplanRepository.findByCode(code).get());
    }

    public List<Flightplan> findAll() {
        var entities = this.flightplanRepository.findAll();
        return this.flightplanFactory.from(entities);
    }

    public void deleteByCode(String code) {
        flightplanRepository.delete(this.flightplanRepository.findByCode(code)
                .orElseThrow(() -> new FlightplanNotFoundException(code)));
    }
}
