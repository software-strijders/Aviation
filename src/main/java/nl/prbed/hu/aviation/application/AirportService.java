package nl.prbed.hu.aviation.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.application.exception.AirportAlreadyExistsException;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.data.airport.SpringAirportRepository;
import nl.prbed.hu.aviation.data.airport.factory.AirportEntityFactory;
import nl.prbed.hu.aviation.domain.Airport;
import nl.prbed.hu.aviation.domain.factory.AirportFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final SpringAirportRepository airportRepository;
    private final AirportEntityFactory airportEntityFactory;
    private final CityService cityService;
    private final AirportFactory airportFactory;

    public Airport create(String code, float longitude, float latitude, String cityName) {
        if (airportRepository.findByCode(code).isPresent())
            throw new AirportAlreadyExistsException(code);
        var entity = airportRepository.save(airportEntityFactory.create(code, longitude, latitude,
                cityService.findCityByName(cityName)));

        return this.airportFactory.createFromEntity(this.airportRepository.save(entity));
    }

    public AirportEntity findByCode(String code) {
        return airportRepository.findByCode(code).orElseThrow(RuntimeException::new);
    }
}
