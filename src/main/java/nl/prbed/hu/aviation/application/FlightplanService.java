package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.data.flightplan.SpringFlightplanRepository;
import nl.prbed.hu.aviation.data.flightplan.factory.FlightPlanEntityFactory;
import nl.prbed.hu.aviation.domain.Flightplan;
import nl.prbed.hu.aviation.domain.factory.FlightplanFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightplanService {
    private final SpringFlightplanRepository fightplanRepository;
    private final FlightPlanEntityFactory flightPlanEntityFactory;
    private final FlightplanFactory flightplanFactory;

    //TODO: fix destination and arrival
    public Flightplan create(Long duration, String code) {
        AirportEntity arrival = null;
        AirportEntity destination = null;
        var entity = flightPlanEntityFactory.create(code, duration, arrival, destination);

        return this.flightplanFactory.from(this.fightplanRepository.save(entity));
    }
}
