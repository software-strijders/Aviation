package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.aircraft.AircraftEntity;
import nl.prbed.hu.aviation.management.data.aircraft.SeatEntity;
import nl.prbed.hu.aviation.management.data.aircraft.SpringAircraftRepository;
import nl.prbed.hu.aviation.management.domain.SeatType;
import nl.prbed.hu.aviation.management.domain.aircraft.Aircraft;
import nl.prbed.hu.aviation.management.domain.aircraft.factory.AircraftFactory;
import nl.prbed.hu.aviation.management.domain.factory.TypeFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AircraftService {
    private static final String ERROR_MSG = "Could not find aircraft with model '%s'";
    private static final String AIRCRAFT_CODE_ERROR_MSG = "Could not find aircraft with code '%s'";

    private final SpringAircraftRepository aircraftRepository;
    private final TypeService typeService;
    private final AirportService airportService;
    private final AircraftFactory aircraftFactory;
    private final TypeFactory typeFactory;

    public Aircraft create(String code, String modelName, int seatsFirst, int seatsBusiness, int seatsEconomy, String airportCode) {
        var type = this.typeService.findTypeEntityByModelName(modelName);
        var aircraft = Aircraft.create()
                .code(code)
                .type(this.typeFactory.from(type))
                .addSeats(seatsFirst, SeatType.FIRST)
                .addSeats(seatsBusiness, SeatType.BUSINESS)
                .addSeats(seatsEconomy, SeatType.ECONOMY)
                .build();

        var seats = aircraft.getSeats().stream().map(x -> new SeatEntity(x.getSeatType(), null)).collect(Collectors.toList());
        var airport = airportService.findAirportEntityByCode(airportCode);
        var entity = new AircraftEntity(code, type, seats, airport);

        return this.aircraftFactory.from(this.aircraftRepository.save(entity));
    }

    public void delete(String code) {
        var aircraft = this.findAircraftEntityByCode(code);
        this.aircraftRepository.delete(aircraft);
    }

    public void deleteByType(String model) {
        var type = this.typeService.findTypeEntityByModelName(model);
        this.aircraftRepository.deleteAircraftEntitiesByType(type);
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
        var entity = this.findAircraftEntityByCode(oldCode);
        entity.setCode(newCode);
        entity.setType(this.typeService.findTypeEntityByModelName(modelName));
        return this.aircraftFactory.from(aircraftRepository.save(entity));
    }

    public AircraftEntity findAircraftEntityByCode(String code) {
        return this.aircraftRepository.findAircraftEntityByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(String.format(AIRCRAFT_CODE_ERROR_MSG, code)));
    }
}
