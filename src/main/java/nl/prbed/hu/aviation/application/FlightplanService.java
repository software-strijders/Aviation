package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.data.flightplan.SpringFlightplanRepository;
import nl.prbed.hu.aviation.data.flightplan.factory.FlightPlanEntityFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightplanService {
    private final SpringFlightplanRepository fightplanRepository;
    private final FlightPlanEntityFactory flightPlanEntityFactory;

    //TODO: fix AirportEntity
    public void create(Long duration, String code) {
        AirportEntity arrival = null;
        AirportEntity destination = null;
        fightplanRepository.save(
                flightPlanEntityFactory.create(
                        code,
                        duration,
                        arrival,
                        destination
                )
        );
    }
}
