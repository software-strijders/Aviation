package nl.prbed.hu.aviation.management.domain.aircraft.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.management.domain.aircraft.Type;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypeFactory {
    public Type from(TypeEntity entity) {
        return new Type(
                entity.getModelName(),
                entity.getManufacturer(),
                entity.getFuelCapacity(),
                entity.getFuelConsumption()
        );
    }
}
