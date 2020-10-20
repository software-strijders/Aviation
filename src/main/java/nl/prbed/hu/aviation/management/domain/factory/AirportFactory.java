package nl.prbed.hu.aviation.management.domain.factory;

import nl.prbed.hu.aviation.management.data.airport.AirportEntity;
import nl.prbed.hu.aviation.management.domain.Airport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirportFactory {

    public Airport from(AirportEntity entity) {
        return new Airport(
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCode(),
                null,
                null
        );
    }

    public List<Airport> from(List<AirportEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
