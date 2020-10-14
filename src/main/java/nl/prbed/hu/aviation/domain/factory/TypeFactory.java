package nl.prbed.hu.aviation.domain.factory;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.domain.Type;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypeFactory {
    public Type createFromEntity(TypeEntity entity) {
        return new Type(
                entity.getModelName(),
                entity.getManufacturer(),
                entity.getFuelCapacity(),
                entity.getFuelConsumption(),
                entity.getNumSeatsFirst(),
                entity.getNumSeatsBusiness(),
                entity.getNumSeatsEconomy()

        );
    }
}
