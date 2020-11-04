package nl.prbed.hu.aviation.management.domain.airport.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.airport.CityEntity;
import nl.prbed.hu.aviation.management.domain.airport.City;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CityFactory {
    private final AirportFactory airportFactory;

    public City from(CityEntity entity) {
        return new City(
                entity.getName(),
                entity.getCountry(),
                this.airportFactory.from(entity.getAirportEntities())
        );
    }
}
