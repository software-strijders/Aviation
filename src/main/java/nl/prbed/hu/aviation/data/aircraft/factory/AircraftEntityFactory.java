package nl.prbed.hu.aviation.data.aircraft.factory;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.domain.Aircraft;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AircraftEntityFactory {
    private final TypeEntityFactory typeEntityFactory;

    public AircraftEntity createFromAircraft(Aircraft aircraft, TypeEntity type) {
        return create(aircraft.getCode(), type);
    }

    public AircraftEntity create(String code, TypeEntity type) {
        return new AircraftEntity(code, type);
    }
}
