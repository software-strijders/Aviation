package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.exception.CityAlreadyExistsException;
import nl.prbed.hu.aviation.application.exception.CityNotFoundException;
import nl.prbed.hu.aviation.data.airport.CityEntity;
import nl.prbed.hu.aviation.data.airport.SpringCityRepository;
import nl.prbed.hu.aviation.domain.City;
import nl.prbed.hu.aviation.domain.factory.CityFactory;
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

        return this.cityFactory.createFromEntity(entity);
    }

    public void delete(String cityName) {
        this.cityRepository.delete(this.findCityEntityByName(cityName));
    }

    public CityEntity findCityEntityByName(String cityName) {
        return cityRepository.findByName(cityName)
                .orElseThrow(() -> new CityNotFoundException(cityName));
    }
}
