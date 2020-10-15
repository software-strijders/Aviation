package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.data.aircraft.SpringAircraftRepository;
import nl.prbed.hu.aviation.data.aircraft.TypeEntity;
import nl.prbed.hu.aviation.data.aircraft.factory.AircraftEntityFactory;
import nl.prbed.hu.aviation.domain.Type;
import nl.prbed.hu.aviation.domain.factory.AircraftFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AircraftService {
    private final SpringAircraftRepository aircraftRepository;
    private final TypeService typeService;
    private final AircraftFactory aircraftFactory;
    private final AircraftEntityFactory aircraftEntityFactory;

    public void create(String code, String modelName) {
        aircraftRepository.save(
                aircraftEntityFactory.create(
                        code,
                        typeService.findTypeEntityByModelName(modelName)
                )
        );
    }

    public void delete(String code) {
        var aircraft = aircraftRepository.findAircraftEntityByCode(code).orElseThrow(RuntimeException::new);
        aircraftRepository.delete(aircraft);
    }

    public void deleteByType(String model) {
        var type = typeService.findTypeEntityByModelName(model);
        aircraftRepository.deleteAircraftEntitiesByType(type);
    }
}
