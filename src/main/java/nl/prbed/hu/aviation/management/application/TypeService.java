package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.application.struct.TypeStruct;
import nl.prbed.hu.aviation.management.data.aircraft.SpringTypeRepository;
import nl.prbed.hu.aviation.management.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.management.domain.aircraft.Type;
import nl.prbed.hu.aviation.management.domain.aircraft.factory.TypeFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeService {
    private static final String ERROR_MSG = "Could not find type with model name: '%s'";

    private final SpringTypeRepository repository;

    private final TypeFactory factory;

    public Type create(TypeStruct struct) {
        var entity = repository.save(new TypeEntity(
                struct.modelName,
                struct.manufacturer,
                struct.fuelCapacity,
                struct.fuelConsumption
        ));
        return this.factory.from(entity);
    }

    public void deleteByName(String name) {
        var entity = this.findTypeEntityByName(name);
        repository.delete(entity);
    }

    public TypeEntity findTypeEntityByName(String name) {
        return this.repository.findByModelName(name)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, name)));
    }
}
