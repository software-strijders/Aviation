package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityAlreadyExistsException;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.airport.CityEntity;
import nl.prbed.hu.aviation.management.data.airport.SpringCityRepository;
import nl.prbed.hu.aviation.management.domain.City;
import nl.prbed.hu.aviation.management.domain.factory.CityFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class CityService {
    private static final String ERROR_MSG = "Could not find city with name: '%s'";
    private static final String DUPLICATE_ERROR_MSG = "City with name: '%s' already exists";

    private final SpringCityRepository repository;

    private final CityFactory factory;

    public City create(String name, String country) {
        if (repository.findByName(name).isPresent())
            throw new EntityAlreadyExistsException(String.format(DUPLICATE_ERROR_MSG, name));

        var entity = repository.save(new CityEntity(name, country, new ArrayList<>()));
        return this.factory.from(entity);
    }

    public void deleteByName(String name) {
        var entity = this.findCityEntityByName(name);
        this.repository.delete(entity);
    }

    public CityEntity findCityEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, name)));
    }
}
