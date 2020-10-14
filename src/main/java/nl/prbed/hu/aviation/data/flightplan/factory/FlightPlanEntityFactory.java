package nl.prbed.hu.aviation.data.flightplan.factory;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.data.flightplan.FlightplanEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FlightPlanEntityFactory {
    public FlightplanEntity create(String code, Long duration, AirportEntity arrival, AirportEntity destination) {
        return new FlightplanEntity(
                null,
                code,
                duration,
                arrival,
                destination
        );
    }
}
