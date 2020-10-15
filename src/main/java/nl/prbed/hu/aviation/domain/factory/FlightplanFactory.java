package nl.prbed.hu.aviation.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.data.flightplan.FlightplanEntity;
import nl.prbed.hu.aviation.domain.Airport;
import nl.prbed.hu.aviation.domain.Flight;
import nl.prbed.hu.aviation.domain.Flightplan;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightplanFactory {
    //TODO: fix destination and arrival
    public Flightplan from(FlightplanEntity flightplanEntity) {
        return new Flightplan(flightplanEntity.getCode(), flightplanEntity.getDuration(), null, null);
    }
}
