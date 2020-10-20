package nl.prbed.hu.aviation.management.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.CityAlreadyExistsException;
import nl.prbed.hu.aviation.management.application.exception.CityNotFoundException;
import nl.prbed.hu.aviation.management.data.airport.CityEntity;
import nl.prbed.hu.aviation.management.data.airport.SpringCityRepository;
import nl.prbed.hu.aviation.management.domain.City;
import nl.prbed.hu.aviation.management.domain.factory.CityFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CityService {
    private final SpringCityRepository cityRepository;
    private final CityFactory cityFactory;

    public City create(String name, String country) {
        if (cityRepository.findByName(name).isPresent())
            throw new CityAlreadyExistsException(name);

        var entity = cityRepository.save(new CityEntity(name, country, new ArrayList<>()));
        return this.cityFactory.from(entity);
    }

    public void delete(String cityName) {
        this.cityRepository.delete(this.findCityEntityByName(cityName));
    }

    public CityEntity findCityEntityByName(String cityName) {
        return cityRepository.findByName(cityName)
                .orElseThrow(() -> new CityNotFoundException(cityName));
    }
}
