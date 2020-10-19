package nl.prbed.hu.aviation.domain.factory;

import nl.prbed.hu.aviation.data.airport.AirportEntity;
import nl.prbed.hu.aviation.domain.Airport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AirportFactory {

    public Airport from(AirportEntity entity) {
        return new Airport(
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCode(),
                null,
                null
        );
    }

    public List<Airport> from(List<AirportEntity> entities) {
        var airports = new ArrayList<Airport>();
        for (var entity : entities) {
            airports.add(from(entity));
        }
        return airports;
    }
}
