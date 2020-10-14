package nl.prbed.hu.aviation.presentation.aircraft.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.AircraftService;
import nl.prbed.hu.aviation.application.TypeService;
import nl.prbed.hu.aviation.presentation.aircraft.dto.CreateAircraftDto;
import nl.prbed.hu.aviation.presentation.aircraft.dto.CreateTypeDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aircraft")
@Secured("ROLE_EMPLOYEE")
@AllArgsConstructor
public class AircraftController {
    private final AircraftService aircraftService;
    private final TypeService typeService;

    @PostMapping("/create")
    public void create(@Validated @RequestBody CreateAircraftDto dto) {
        aircraftService.create(
                dto.code,
                dto.modelName
        );
    }

    @PostMapping("/type")
    public void create(@Validated @RequestBody CreateTypeDto dto) {
        typeService.create(
                dto.modelName,
                dto.manufacturer,
                dto.fuelCapacity,
                dto.fuelConsumption,
                dto.numSeatsFirst,
                dto.numSeatsBusiness,
                dto.numSeatsEconomy);
    }
}
