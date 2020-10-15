package nl.prbed.hu.aviation.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.domain.Airport;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AirportFactory {
    private final CityFactory cityFactory;

    public Airport createFromEntity(AirportEntity entity) {
        return new Airport(
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCode(),
                cityFactory.createFromEntity(entity.getCity()),
                null
        );
    }
}
