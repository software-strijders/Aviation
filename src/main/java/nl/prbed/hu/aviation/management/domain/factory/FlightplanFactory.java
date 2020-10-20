package nl.prbed.hu.aviation.management.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.flightplan.FlightplanEntity;
import nl.prbed.hu.aviation.management.domain.Flightplan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FlightplanFactory {
    private final AirportFactory airportFactory;

    public Flightplan from(FlightplanEntity flightplanEntity) {
        return new Flightplan(
                flightplanEntity.getCode(),
                flightplanEntity.getDuration(),
                airportFactory.from(flightplanEntity.getArrival()),
                airportFactory.from(flightplanEntity.getDestination())
        );
    }

    public List<Flightplan> from(List<FlightplanEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
