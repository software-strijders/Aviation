package nl.prbed.hu.aviation.management.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.airport.AirportEntity;
import nl.prbed.hu.aviation.management.domain.Airport;
import nl.prbed.hu.aviation.management.domain.aircraft.factory.AircraftFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AirportFactory {
    private final AircraftFactory aircraftFactory;

    public Airport from(AirportEntity entity) {
        return new Airport(
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCode(),
                null, // This will be set later
                entity.getAircraftEntities() != null ? aircraftFactory.from(entity.getAircraftEntities()) : null
        );
    }

    public List<Airport> from(List<AirportEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
