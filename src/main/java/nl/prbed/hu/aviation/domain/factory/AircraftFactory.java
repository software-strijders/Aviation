package nl.prbed.hu.aviation.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.domain.Aircraft;
import nl.prbed.hu.aviation.domain.Type;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AircraftFactory {
    private TypeFactory typeFactory;

    public Aircraft create(Type type) {
        return new Aircraft(
                "KLM74701",
                type,
                null,
                null,
                null);
    }

    public Aircraft createFromEntity(AircraftEntity entity) {
        return new Aircraft(
                entity.getCode(),
                typeFactory.createFromEntity(entity.getType()),
                null,
                null,
                null);
    }
}
