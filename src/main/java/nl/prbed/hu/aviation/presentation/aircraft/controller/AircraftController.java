package nl.prbed.hu.aviation.presentation.aircraft.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.AircraftService;
import nl.prbed.hu.aviation.application.TypeService;
import nl.prbed.hu.aviation.presentation.aircraft.dto.CreateAircraftDto;
import nl.prbed.hu.aviation.presentation.aircraft.dto.CreateTypeDto;
import nl.prbed.hu.aviation.presentation.aircraft.dto.DeleteAircraftDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aircraft")
@Secured("ROLE_EMPLOYEE")
@AllArgsConstructor
public class AircraftController {
    private final AircraftService aircraftService;
    private final TypeService typeService;

    @PostMapping
    public void create(@Validated @RequestBody CreateAircraftDto dto) {
        aircraftService.create(
                dto.code,
                dto.modelName
        );
    }

    @DeleteMapping("/{code}")
    public void delete(@Validated @PathVariable String code) {
        aircraftService.delete(code);
    }

    @PostMapping("/type")
    public void createType(@Validated @RequestBody CreateTypeDto dto) {
        typeService.create(
                dto.modelName,
                dto.manufacturer,
                dto.fuelCapacity,
                dto.fuelConsumption,
                dto.numSeatsFirst,
                dto.numSeatsBusiness,
                dto.numSeatsEconomy);
    }

    @Transactional
    @DeleteMapping("/type/{model}")
    public void deleteType(@Validated @PathVariable String model) {
        aircraftService.deleteByType(model);
        typeService.delete(model);
    }
}
