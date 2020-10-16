package nl.prbed.hu.aviation.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.domain.Airport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AirportFactory {
    private final CityFactory cityFactory;

    public Airport from(AirportEntity entity) {
        return this.from(entity, false);
    }

    public Airport from(AirportEntity entity, boolean shouldCallCityFactory) {
        return new Airport(
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCode(),
                shouldCallCityFactory ? cityFactory.createFromEntity(entity.getCity()) : null,
                null
        );
    }

    public List<Airport> from(List<AirportEntity> entities) {
        var airports = new ArrayList<Airport>();
        for (var entity : entities) {
            airports.add(from(entity, false));
        }
        return airports;
    }
}
