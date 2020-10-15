package nl.prbed.hu.aviation.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.data.aircraft.SpringAircraftRepository;
import nl.prbed.hu.aviation.data.aircraft.factory.AircraftEntityFactory;
import nl.prbed.hu.aviation.domain.Aircraft;
import nl.prbed.hu.aviation.domain.factory.AircraftFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftService {
    private final SpringAircraftRepository aircraftRepository;
    private final TypeService typeService;
    private final AircraftFactory aircraftFactory;
    private final AircraftEntityFactory aircraftEntityFactory;

    public Aircraft create(String code, String modelName) {
        var type = this.typeService.findTypeEntityByModelName(modelName);
        var entity = this.aircraftEntityFactory.create(code, type);
        return this.aircraftFactory.from(this.aircraftRepository.save(entity));
    }

    public List<Aircraft> findAllByType(String modelName) {
        var type = this.typeService.findTypeEntityByModelName(modelName);
        var entities = this.aircraftRepository.findAircraftEntitiesByType(type);
        return this.aircraftFactory.from(entities);
    }

    public List<Aircraft> findAll() {
        var entities = this.aircraftRepository.findAll();
        return this.aircraftFactory.from(entities);
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
