package nl.prbed.hu.aviation.management.presentation.aircraft.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.management.application.AircraftService;
import nl.prbed.hu.aviation.management.application.TypeService;
import nl.prbed.hu.aviation.management.presentation.aircraft.dto.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Secured("ROLE_EMPLOYEE")
@RequestMapping("/aircraft")
public class AircraftController {
    private final AircraftService aircraftService;
    private final TypeService typeService;

    @ApiOperation(
            value = "Delete an aircraft",
            notes = "Provide the code of the aircraft."
    )
    @DeleteMapping("/{code}")
    public void deleteByCode(@Validated @PathVariable String code) {
        this.aircraftService.deleteByCode(code);
    }

    @ApiOperation(
            value = "Delete an aircraft type",
            notes = "Provide the model name of the type. This will also delete all aircraft of this type."
    )
    @DeleteMapping("/type/{modelName}")
    public void deleteTypeByModel(@Validated @PathVariable String modelName) {
        this.aircraftService.deleteByType(modelName);
        this.typeService.deleteByName(modelName);
    }


    @ApiOperation(value = "Find all aircraft")
    @GetMapping
    public AircraftOverviewResponseDto findAll() {
        var aircraft = this.aircraftService.findAll();
        return new AircraftOverviewResponseDto(aircraft);
    }

    @ApiOperation(
            value = "Find all aircraft by model",
            notes = "Provide the model name of the aircraft."
    )
    @GetMapping("/{modelName}")
    public AircraftOverviewResponseDto findAllByModel(@PathVariable String modelName) {
        var aircraft = this.aircraftService.findAllByType(modelName);
        return new AircraftOverviewResponseDto(aircraft);
    }

    @ApiOperation(
            value = "Create an aircraft",
            notes = "Provide the details of an aircraft to create an aircraft of a type. " +
                    "Note that the type must exist before you can make an aircraft of it."
    )
    @PostMapping
    public AircraftResponseDto create(@Validated @RequestBody CreateAircraftDto dto) {
        var aircraft = this.aircraftService.create(
                dto.code,
                dto.modelName,
                dto.seatsFirst,
                dto.seatsBusiness,
                dto.seatsEconomy,
                dto.airportCode
        );
        return new AircraftResponseDto(aircraft.getCode(), aircraft.getType().getModelName(), aircraft.getSeats());
    }

    @ApiOperation(
            value = "Create an aircraft type",
            notes = "Provide the details of the type."
    )
    @PostMapping("/type")
    public TypeResponseDto create(@Validated @RequestBody CreateTypeDto dto) {
        var type = this.typeService.create(
                dto.modelName,
                dto.manufacturer,
                dto.fuelCapacity,
                dto.fuelConsumption
        );
        return new TypeResponseDto(type);
    }

    @ApiOperation(
            value = "Update aircraft information",
            notes = "Provide the code of the aircraft."
    )
    @PatchMapping({"/{code}"})
    public AircraftResponseDto update(@Validated @PathVariable String code, @Validated @RequestBody UpdateAircraftDto dto) {
        var aircraft = this.aircraftService.update(code, dto.code, dto.modelName);
        return new AircraftResponseDto(aircraft.getCode(), aircraft.getType().getModelName(), aircraft.getSeats());
    }
}
