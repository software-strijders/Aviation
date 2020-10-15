package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
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
    private final FlightPlanEntityFactory flightPlanEntityFactory;
    private final FlightplanFactory flightplanFactory;

    //TODO: fix destination and arrival
    public Flightplan create(Long duration, String code) {
        AirportEntity arrival = null;
        AirportEntity destination = null;
        var entity = flightPlanEntityFactory.create(code, duration, arrival, destination);

        return this.flightplanFactory.from(this.flightplanRepository.save(entity));
    }

    public Flightplan findByCode(String code) {
        return this.flightplanFactory.from(this.flightplanRepository.findByCode(code).get());
    }

    public List<Flightplan> findAll() {
        var entities = this.flightplanRepository.findAll();
        return this.flightplanFactory.from(entities);
    }
}