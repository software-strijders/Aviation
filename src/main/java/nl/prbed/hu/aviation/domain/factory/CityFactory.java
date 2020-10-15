package nl.prbed.hu.aviation.domain.factory;

import nl.prbed.hu.aviation.data.airport.CityEntity;
import nl.prbed.hu.aviation.domain.City;
import org.springframework.stereotype.Component;

@Component
public class CityFactory {
    public City createFromEntity(CityEntity entity) {
        return new City(
                entity.getName(),
                entity.getCountry(),
                null // check if 1on1 bidirectional is necessary
        );
    }
}
