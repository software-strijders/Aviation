package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.TypeNotFoundException;
import nl.prbed.hu.aviation.management.data.aircraft.SpringTypeRepository;
import nl.prbed.hu.aviation.management.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.management.data.aircraft.factory.TypeEntityFactory;
import nl.prbed.hu.aviation.management.domain.Type;
import nl.prbed.hu.aviation.management.domain.factory.TypeFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
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
                .orElseThrow(() -> new TypeNotFoundException(modelName));
    }


    // TODO: This should throw an exception if there are aircrafts connected to the type
    public void delete(String model) {
        var type = typeRepository.findByModelName(model).orElseThrow();
        typeRepository.delete(type);
    }
}
