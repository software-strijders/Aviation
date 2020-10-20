package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.AirportAlreadyExistsException;
import nl.prbed.hu.aviation.management.application.exception.AirportNotFoundException;
import nl.prbed.hu.aviation.management.data.airport.AirportEntity;
import nl.prbed.hu.aviation.management.data.airport.SpringAirportRepository;
import nl.prbed.hu.aviation.management.domain.Airport;
import nl.prbed.hu.aviation.management.domain.factory.AirportFactory;
import nl.prbed.hu.aviation.management.domain.factory.CityFactory;
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
        var entities = this.airportRepository.findAirportEntitiesByCityName(name)
                .orElseThrow(() -> new AirportNotFoundException(name));
        return this.airportFactory.from(entities);
    }

    public Airport update(String oldCode, String newCode, double longitude, double latitude, String city) {
        var airportEntity = this.findAirportEntityByCode(oldCode);
        airportEntity.setCode(newCode);
        airportEntity.setLongitude(longitude);
        airportEntity.setLatitude(latitude);
        var cityEntity = this.cityService.findCityEntityByName(city);
        airportEntity.setCity(cityEntity);
        var airport = this.airportFactory.from(airportRepository.save(airportEntity));
        airport.setCity(this.cityFactory.createFromEntity(cityEntity));
        return airport;
    }
    private AirportEntity findAirportEntityByCode(String code) {
        return this.airportRepository.findByCode(code)
                .orElseThrow(() -> new AirportNotFoundException(code));
    }
}
