package nl.prbed.hu.aviation.management.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.airport.CityEntity;
import nl.prbed.hu.aviation.management.domain.City;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CityFactory {
    private final AirportFactory airportFactory;

    public City from(CityEntity entity) {
        return new City(
                entity.getName(),
                entity.getCountry(),
                airportFactory.from(entity.getAirportEntities())
        );
    }
}
