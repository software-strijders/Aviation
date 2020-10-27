package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.aircraft.SpringTypeRepository;
import nl.prbed.hu.aviation.management.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.management.domain.Type;
import nl.prbed.hu.aviation.management.domain.factory.TypeFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeService {
    private static final String ERROR_MSG = "Could not find type with model name: '%s'";

    private final SpringTypeRepository typeRepository;

    private final TypeFactory typeFactory;

    public Type create(String modelName, String manufacturer, int fuelCapacity, int fuelConsumption) {
        var entity = typeRepository.save(new TypeEntity(
                modelName,
                manufacturer,
                fuelCapacity,
                fuelConsumption
        ));
        return this.typeFactory.from(entity);
    }

    public void deleteByName(String name) {
        var entity = this.findTypeEntityByName(name);
        typeRepository.delete(entity);
    }

    public TypeEntity findTypeEntityByName(String name) {
        return this.typeRepository.findByModelName(name)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, name)));
    }
}
