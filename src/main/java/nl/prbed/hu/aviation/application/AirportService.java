package nl.prbed.hu.aviation.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.application.exception.AirportAlreadyExistsException;
import nl.prbed.hu.aviation.application.exception.AirportNotFoundException;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.data.airport.SpringAirportRepository;
import nl.prbed.hu.aviation.data.airport.SpringCityRepository;
import nl.prbed.hu.aviation.domain.Airport;
import nl.prbed.hu.aviation.domain.factory.AirportFactory;
import nl.prbed.hu.aviation.domain.factory.CityFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final SpringAirportRepository airportRepository;
    private final AirportFactory airportFactory;
    private final CityService cityService;
    private final CityFactory cityFactory;

    public Airport create(String code, double longitude, double latitude, String cityName) {
        if (airportRepository.findByCode(code).isPresent())
            throw new AirportAlreadyExistsException(code);

        var city = this.cityService.findCityEntityByName(cityName);
        var entity = airportRepository.save(new AirportEntity(code, longitude, latitude, city));
        return this.airportFactory.from(entity);
    }

    public void delete(String code) {
        this.airportRepository.delete(this.findEntityByCode(code));
    }

    public Airport findByCode(String code) {
        return this.airportFactory.from(this.findEntityByCode(code));
    }

    private AirportEntity findEntityByCode(String code) {
        return airportRepository.findByCode(code)
                .orElseThrow(() -> new AirportNotFoundException(code));
    }

    public List<Airport> findAll() {
        var entities = this.airportRepository.findAll();
        return this.airportFactory.from(entities);
    }

    public List<Airport> findByCity(String name) {
        var entities = this.airportRepository.findAirportEntitiesByCityName(name)
                .orElseThrow(() -> new AirportNotFoundException(name));
        return this.airportFactory.from(entities);
    }

    public Airport update(String oldCode, String newCode, double longitude, double latitude, String city) {
        var airportEntity = this.airportRepository.findByCode(oldCode)
                .orElseThrow(() -> new AirportNotFoundException(oldCode));
        airportEntity.setCode(newCode);
        airportEntity.setLongitude(longitude);
        airportEntity.setLatitude(latitude);
        var cityEntity = cityService.findCityEntityByName(city);
        airportEntity.setCity(cityEntity);
        var airport = airportFactory.from(airportRepository.save(airportEntity));
        airport.setCity(cityFactory.createFromEntity(cityEntity));
        return airport;
    }
}
