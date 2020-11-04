package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.application.struct.AircraftStruct;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.data.aircraft.SeatEntity;
import nl.prbed.hu.aviation.management.data.aircraft.SpringAircraftRepository;
import nl.prbed.hu.aviation.management.domain.aircraft.SeatType;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;
import nl.prbed.hu.aviation.management.domain.aircraft.factory.AircraftFactory;
import nl.prbed.hu.aviation.management.domain.aircraft.factory.TypeFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AircraftService {
    private static final String AIRCRAFT_CODE_ERROR_MSG = "Could not find aircraft with code: '%s'";

    private final SpringAircraftRepository repository;

    private final TypeService typeService;
    private final AirportService airportService;
    private final AircraftFactory aircraftFactory;

    private final TypeFactory factory;

    public Aircraft create(AircraftStruct struct) {
        var type = this.typeService.findTypeEntityByName(struct.modelName);
        var aircraft = Aircraft.create()
                .code(struct.code)
                .type(this.factory.from(type))
                .addSeats(struct.seatsFirst, SeatType.FIRST)
                .addSeats(struct.seatsBusiness, SeatType.BUSINESS)
                .addSeats(struct.seatsEconomy, SeatType.ECONOMY)
                .build();

        var seats = aircraft.getSeats().stream().map(x -> new SeatEntity(x.getSeatType(), null, x.getSeatNumber())).collect(Collectors.toList());
        var airport = airportService.findAirportEntityByCode(struct.airportCode);
        var entity = new AircraftEntity(struct.code, type, seats, airport);
        return this.aircraftFactory.from(this.repository.save(entity));
    }

    public void deleteByCode(String code) {
        var entity = this.findAircraftEntityByCode(code);
        this.repository.delete(entity);
    }

    public void deleteByType(String modelName) {
        var entity = this.typeService.findTypeEntityByName(modelName);
        this.repository.deleteAircraftEntitiesByType(entity);
    }

    public List<Aircraft> findAllByType(String modelName) {
        var type = this.typeService.findTypeEntityByName(modelName);
        var entities = this.repository.findAircraftEntitiesByType(type);
        return this.aircraftFactory.from(entities);
    }

    public List<Aircraft> findAll() {
        var entities = this.repository.findAll();
        return this.aircraftFactory.from(entities);
    }

    // Fleet isn't implemented yet, that's the reason it isn't given as a parameter.
    public Aircraft update(String oldCode, String newCode, String modelName) {
        var entity = this.findAircraftEntityByCode(oldCode);
        entity.setCode(newCode);
        entity.setType(this.typeService.findTypeEntityByName(modelName));
        return this.aircraftFactory.from(repository.save(entity));
    }

    public AircraftEntity findAircraftEntityByCode(String code) {
        return this.repository.findAircraftEntityByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(AIRCRAFT_CODE_ERROR_MSG, code)));
    }
}
