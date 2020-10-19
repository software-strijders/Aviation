package nl.prbed.hu.aviation.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.domain.Airport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AirportFactory {
    private final CityFactory cityFactory;

    public Airport from(AirportEntity entity) {
        return new Airport(
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCode(),
                cityFactory.createFromEntity(entity.getCity()),
                null
        );
    }

    public List<Airport> from(List<AirportEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
