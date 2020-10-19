package nl.prbed.hu.aviation.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.data.airport.CityEntity;
import nl.prbed.hu.aviation.domain.City;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CityFactory {
    private final AirportFactory airportFactory;

    public City createFromEntity(CityEntity entity) {
        return new City(
                entity.getName(),
                entity.getCountry(),
                airportFactory.from(entity.getAirportEntities())
        );
    }
}
