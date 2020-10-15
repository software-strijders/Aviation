package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.data.airport.CityEntity;
import nl.prbed.hu.aviation.data.airport.SpringCityRepository;
import nl.prbed.hu.aviation.data.airport.factory.CityEntityFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CityService {
    private final SpringCityRepository cityRepository;
    private final CityEntityFactory cityEntityFactory;

    public void create(
            String name,
            String country
    ) {
        cityRepository.save(new CityEntity(
                null,
                name,
                country,
                new ArrayList<>()
        ));
    }

    public void addAirportToCity(AirportEntity airportEntity, String cityName) {
        var city = cityRepository.findByName(cityName).orElseThrow(RuntimeException::new);
        city.addAirport(airportEntity);
        cityRepository.save(city);
    }

    public CityEntity findCityByName(String cityName) {
        return cityRepository.findByName(cityName).orElseThrow(RuntimeException::new);
    }
}
