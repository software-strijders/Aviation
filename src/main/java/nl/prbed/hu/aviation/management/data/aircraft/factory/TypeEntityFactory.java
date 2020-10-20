package nl.prbed.hu.aviation.management.data.aircraft.factory;

import nl.prbed.hu.aviation.management.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.management.domain.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeEntityFactory {
    public TypeEntity create(Type type) {
        return new TypeEntity(
                null,
                type.getModelName(),
                type.getManufacturer(),
                type.getFuelCapacity(),
                type.getFuelConsumption(),
                type.getNumSeatsFirst(),
                type.getNumSeatsBusiness(),
                type.getNumSeatsEconomy()
        );
    }
}
