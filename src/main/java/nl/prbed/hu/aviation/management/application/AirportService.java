package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityAlreadyExistsException;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.data.aircraft.SpringAircraftRepository;
import nl.prbed.hu.aviation.management.data.airport.AirportEntity;
import nl.prbed.hu.aviation.management.data.airport.SpringAirportRepository;
import nl.prbed.hu.aviation.management.domain.Airport;
import nl.prbed.hu.aviation.management.domain.factory.AirportFactory;
import nl.prbed.hu.aviation.management.domain.factory.CityFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private static final String ERROR_MSG = "Could not find airport with code '%s'";
    private static final String DUPLICATE_ERROR_MSG = "Airport with code: '%s' already exists";

    private final SpringAirportRepository airportRepository;
    private final SpringAircraftRepository aircraftRepository;
    private final AirportFactory airportFactory;
    private final CityService cityService;
    private final CityFactory cityFactory;

    public Airport create(String code, double longitude, double latitude, String cityName) {
        if (this.airportRepository.findByCode(code).isPresent())
            throw new EntityAlreadyExistsException(String.format(DUPLICATE_ERROR_MSG, code));

        var city = this.cityService.findCityEntityByName(cityName);
        var entity = airportRepository.save(new AirportEntity(code, longitude, latitude, city, null));
        return this.airportFactory.from(entity);
    }

    public void delete(String code) {
        this.airportRepository.delete(this.findAirportEntityByCode(code));
    }

    public Airport findByCode(String code) {
        return this.airportFactory.from(this.findAirportEntityByCode(code));
    }

    public List<Airport> findAll() {
        var entities = this.airportRepository.findAll();
        return this.airportFactory.from(entities);
    }

    public List<Airport> findByCity(String name) {
        var entities = this.airportRepository.findAirportEntitiesByCityName(name);
        return this.airportFactory.from(entities);
    }

    public Airport update(String oldCode, String newCode, double longitude, double latitude, String city, List<String> airportCodes) {
        var airportEntity = this.findAirportEntityByCode(oldCode);
        airportEntity.setCode(newCode);
        airportEntity.setLongitude(longitude);
        airportEntity.setLatitude(latitude);
        airportEntity.setAircraftEntities(this.findByAircraftCodes(airportCodes));
        var cityEntity = this.cityService.findCityEntityByName(city);
        airportEntity.setCity(cityEntity);
        var airport = this.airportFactory.from(airportRepository.save(airportEntity));
        airport.setCity(this.cityFactory.from(cityEntity));
        return airport;
    }

    public AirportEntity findAirportEntityByCode(String code) {
        return this.airportRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code)));
    }

    public Airport addAircraftsToAirport(String airportCode, List<String> aircraftCodes) {
        var airport = findAirportEntityByCode(airportCode);
        airport.getAircraftEntities().addAll(findByAircraftCodes(aircraftCodes));

        return this.airportFactory.from(this.airportRepository.save(airport));
    }

    public Airport removeAircraftFromAirport(String airportCode, String aircraftCode) {
        var airport = findAirportEntityByCode(airportCode);
        airport.getAircraftEntities().remove(aircraftRepository.findAircraftEntityByCode(aircraftCode)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, aircraftCode))));
        return this.airportFactory.from(this.airportRepository.save(airport));
    }

    private List<AircraftEntity> findByAircraftCodes(List<String> aircraftCodes) {
        var aircraftEntities = new ArrayList<AircraftEntity>();
        for (var code : aircraftCodes)
            aircraftEntities.add(aircraftRepository.findAircraftEntityByCode(code)
                    .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, code))));
        return aircraftEntities;
    }
}
