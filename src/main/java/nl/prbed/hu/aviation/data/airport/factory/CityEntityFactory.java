package nl.prbed.hu.aviation.data.airport.factory;

import nl.prbed.hu.aviation.data.airport.CityEntity;
import nl.prbed.hu.aviation.domain.City;
import org.springframework.stereotype.Component;

@Component
public class CityEntityFactory {
    public CityEntity create(City city) {
        return new CityEntity(
                null,
                city.getName(),
                city.getCountry(),
                null
        );
    }
}
