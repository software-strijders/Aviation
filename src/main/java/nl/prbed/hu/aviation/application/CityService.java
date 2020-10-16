package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.exception.CityAlreadyExistsException;
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
        if (cityRepository.findByName(name).isPresent())
            throw new CityAlreadyExistsException(name);
        cityRepository.save(new CityEntity(
                null,
                name,
                country,
                new ArrayList<>()
        ));
    }

    public CityEntity findCityByName(String cityName) {
        return cityRepository.findByName(cityName).orElseThrow(RuntimeException::new);
    }
}
