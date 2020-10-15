package nl.prbed.hu.aviation.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.application.exception.ModelNotFoundException;
import nl.prbed.hu.aviation.data.aircraft.SpringTypeRepository;
import nl.prbed.hu.aviation.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.data.aircraft.factory.TypeEntityFactory;
import nl.prbed.hu.aviation.domain.Type;
import nl.prbed.hu.aviation.domain.factory.TypeFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final SpringTypeRepository typeRepository;
    private final TypeEntityFactory typeEntityFactory;
    private final TypeFactory typeFactory;

    public Type create(
            String modelName,
            String manufacturer,
            int fuelCapacity,
            int fuelConsumption,
            int numSeatsFirst,
            int numSeatsBusiness,
            int numSeatsEconomy
    ) {
        var entity = typeRepository.save(new TypeEntity(
                null,
                modelName,
                manufacturer,
                fuelCapacity,
                fuelConsumption,
                numSeatsFirst,
                numSeatsBusiness,
                numSeatsEconomy
        ));
        return this.typeFactory.from(entity);
    }

    public TypeEntity findTypeEntityByModelName(String modelName) {
        return this.typeRepository.findByModelName(modelName)
                .orElseThrow(() -> new ModelNotFoundException(modelName));
    }

    public void delete(String model) {
        var type = typeRepository.findByModelName(model).orElseThrow();
        typeRepository.delete(type);
    }
}
