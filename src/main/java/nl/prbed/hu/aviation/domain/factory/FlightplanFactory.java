package nl.prbed.hu.aviation.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Airport;
import nl.prbed.hu.aviation.domain.Flightplan;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightplanFactory {
    public Flightplan create(String code, Long duration, Airport destination, Airport arrival) {
        return new Flightplan(
                code,
                duration,
                destination,
                arrival
        );
    }
}
