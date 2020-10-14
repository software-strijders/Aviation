package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.data.aircraft.SpringTypeRepository;
import nl.prbed.hu.aviation.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.data.aircraft.factory.TypeEntityFactory;
import nl.prbed.hu.aviation.domain.factory.TypeFactory;
import nl.prbed.hu.aviation.presentation.dto.CreateTypeDto;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class TypeService {
    private final SpringTypeRepository typeRepository;
    private final TypeEntityFactory typeEntityFactory;
    private final TypeFactory typeFactory;

    public void create(
            String modelName,
            String manufacturer,
            int fuelCapacity,
            int fuelConsumption,
            int numSeatsFirst,
            int numSeatsBusiness,
            int numSeatsEconomy) {
        typeRepository.save(new TypeEntity(
                null,
                modelName,
                manufacturer,
                fuelCapacity,
                fuelConsumption,
                numSeatsFirst,
                numSeatsBusiness,
                numSeatsEconomy
        ));
    }

    public TypeEntity findTypeEntityByModelName(String modelName) {
        return typeRepository.findByModelName(modelName).orElseThrow(RuntimeException::new);
    }
}
