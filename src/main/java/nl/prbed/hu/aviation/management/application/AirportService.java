package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityAlreadyExistsException;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.application.struct.AirportStruct;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.data.aircraft.SpringAircraftRepository;
import nl.prbed.hu.aviation.management.data.airport.AirportEntity;
import nl.prbed.hu.aviation.management.data.airport.SpringAirportRepository;
import nl.prbed.hu.aviation.management.domain.Airport;
import nl.prbed.hu.aviation.management.domain.factory.AirportFactory;
import nl.prbed.hu.aviation.management.domain.factory.CityFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AirportService {
    private static final String ERROR_MSG = "Could not find airport with code: '%s'";
    private static final String DUPLICATE_ERROR_MSG = "Airport with code: '%s' already exists";

    private final SpringAirportRepository airportRepository;
    private final SpringAircraftRepository aircraftRepository;

    private final CityService cityService;

    private final CityFactory cityFactory;
    private final AirportFactory airportFactory;

    public Airport addAircraftToAirport(String airportCode, List<String> aircraftCodes) {
        var entity = findAirportEntityByCode(airportCode);
        entity.getAircraftEntities().addAll(findByAircraftCodes(aircraftCodes));
        return this.airportFactory.from(this.airportRepository.save(entity));
    }

    public Airport create(AirportStruct struct) {
        if (this.airportRepository.findByCode(struct.code).isPresent())
            throw new EntityAlreadyExistsException(String.format(DUPLICATE_ERROR_MSG, struct.code));

        var city = this.cityService.findCityEntityByName(struct.cityName);
        var entity = airportRepository.save(new AirportEntity(struct.code, struct.longitude, struct.latitude, city, null));
        return this.airportFactory.from(entity);
    }

    public void deleteByCode(String code) {
        var entity = this.findAirportEntityByCode(code);
        this.airportRepository.delete(entity);
    }

    public AirportEntity findAirportEntityByCode(String code) {
        return this.airportRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code)));
    }

    public Airport findByCode(String code) {
        var entity = this.findAirportEntityByCode(code);
        return this.airportFactory.from(entity);
    }

    public List<Airport> findAll() {
        var entities = this.airportRepository.findAll();
        return this.airportFactory.from(entities);
    }

    public List<Airport> findByCity(String name) {
        var entities = this.airportRepository.findAirportEntitiesByCityName(name);
        return this.airportFactory.from(entities);
    }


    public Airport update(String oldCode, AirportStruct struct) {
        var entity = this.findAirportEntityByCode(oldCode);
        entity.setCode(struct.code);
        entity.setLongitude(struct.longitude);
        entity.setLatitude(struct.latitude);
        entity.setAircraftEntities(this.findByAircraftCodes(struct.aircraftCodes));
        var city = this.cityService.findCityEntityByName(struct.cityName);
        entity.setCity(city);
        var airport = this.airportFactory.from(airportRepository.save(entity));
        airport.setCity(this.cityFactory.from(city));
        return airport;
    }

    public Airport removeAircraftFromAirport(String code, String aircraftCode) {
        var entity = findAirportEntityByCode(code);
        entity.getAircraftEntities().remove(aircraftRepository.findAircraftEntityByCode(aircraftCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, aircraftCode))));
        return this.airportFactory.from(this.airportRepository.save(entity));
    }

    private List<AircraftEntity> findByAircraftCodes(List<String> aircraftCodes) {
        var entities = new ArrayList<AircraftEntity>();
        for (var code : aircraftCodes)
            entities.add(aircraftRepository.findAircraftEntityByCode(code)
                    .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code))));
        return entities;
    }
}
