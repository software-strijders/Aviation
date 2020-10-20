package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.AircraftNotFoundException;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.data.aircraft.SpringAircraftRepository;
import nl.prbed.hu.aviation.management.domain.Aircraft;
import nl.prbed.hu.aviation.management.domain.factory.AircraftFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AircraftService {
    private final SpringAircraftRepository aircraftRepository;
    private final TypeService typeService;
    private final AircraftFactory aircraftFactory;

    public Aircraft create(String code, String modelName) {
        var type = this.typeService.findTypeEntityByModelName(modelName);
        var entity = new AircraftEntity(code, type);
        return this.aircraftFactory.from(this.aircraftRepository.save(entity));
    }

    public void delete(String code) {
        var aircraft = aircraftRepository.findAircraftEntityByCode(code)
                .orElseThrow(() -> new AircraftNotFoundException(code));
        aircraftRepository.delete(aircraft);
    }

    public void deleteByType(String model) {
        var type = typeService.findTypeEntityByModelName(model);
        aircraftRepository.deleteAircraftEntitiesByType(type);
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

    // Fleet isn't implemented yet, that's the reason it isn't given as a parameter.
    public Aircraft update(String oldCode, String newCode, String modelName) {
        var entity = this.aircraftRepository.findAircraftEntityByCode(oldCode)
                .orElseThrow(() -> new AircraftNotFoundException(oldCode));
        entity.setCode(newCode);
        entity.setType(typeService.findTypeEntityByModelName(modelName));
        return aircraftFactory.from(aircraftRepository.save(entity));
    }
}
