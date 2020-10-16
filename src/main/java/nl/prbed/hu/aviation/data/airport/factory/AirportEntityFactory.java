package nl.prbed.hu.aviation.data.airport.factory;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.data.airport.CityEntity;
import nl.prbed.hu.aviation.domain.Airport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AirportEntityFactory {
    private final CityEntityFactory cityEntityFactory;

    public AirportEntity createFromAirport(Airport airport, CityEntity city) {
        return create(
                null,
                airport.getCode(),
                airport.getLongitude(),
                airport.getLatitude(),
                city
        );
    }

    public AirportEntity create(String code, float longitude, float latitude, CityEntity city) {
        return create(null, code, longitude, latitude, city);
    }

    private AirportEntity create(Long id, String code, float latitude, float longitude, CityEntity city) {
        return new AirportEntity(id, code, longitude, latitude, city);
    }
}
