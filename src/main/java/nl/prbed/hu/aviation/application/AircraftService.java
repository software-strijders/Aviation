package nl.prbed.hu.aviation.application;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.data.aircraft.SpringAircraftRepository;
import nl.prbed.hu.aviation.data.aircraft.factory.AircraftEntityFactory;
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
}
