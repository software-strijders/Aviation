package nl.prbed.hu.aviation.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.domain.Aircraft;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AircraftFactory {
    private final TypeFactory typeFactory;

    public Aircraft from(AircraftEntity entity) {
        return new Aircraft(
                entity.getCode(),
                this.typeFactory.from(entity.getType()),
                null,
                null,
                null);
    }

    public List<Aircraft> from(List<AircraftEntity> entities) {
        var list = new ArrayList<Aircraft>();
        for (var entity : entities)
            list.add(this.from(entity));
        return list;
    }
}
