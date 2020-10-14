package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.prbed.hu.aviation.data.aircraft.SpringAircraftRepository;
import nl.prbed.hu.aviation.data.aircraft.SpringTypeRepository;
import nl.prbed.hu.aviation.data.aircraft.factory.AircraftEntityFactory;
import nl.prbed.hu.aviation.data.aircraft.factory.TypeEntityFactory;
import nl.prbed.hu.aviation.domain.factory.AircraftFactory;
import nl.prbed.hu.aviation.domain.factory.TypeFactory;
import nl.prbed.hu.aviation.presentation.dto.CreateAircraftDto;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class AircraftService {
    private final SpringAircraftRepository aircraftRepository;
    private final TypeService typeService;
    private final AircraftFactory aircraftFactory;
    private final AircraftEntityFactory aircraftEntityFactory;

    public void create(CreateAircraftDto dto) {
        aircraftRepository.save(
                aircraftEntityFactory.create(
                        dto.code,
                        typeService.findTypeEntityByModelName(dto.modelName)
                )
        );
    }
}
