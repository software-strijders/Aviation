package nl.prbed.hu.aviation.presentation.aircraft.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.AircraftService;
import nl.prbed.hu.aviation.application.TypeService;
import nl.prbed.hu.aviation.presentation.aircraft.dto.*;
import org.springframework.lang.NonNull;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aircraft")
@Secured("ROLE_EMPLOYEE")
@AllArgsConstructor
public class AircraftController {
    private final AircraftService aircraftService;
    private final TypeService typeService;

    @ApiOperation(
            value = "Update aircraft information",
            notes = "Provide a code to update specific information about an aircraft."
    )
    @PutMapping({"/{code}"})
    public UpdateAircraftResponseDto update(@Validated @PathVariable @NonNull String code, @Validated @RequestBody UpdateAircraftDto dto) {
        var aircraft = this.aircraftService.update(code, dto.code, dto.modelName);
        return new UpdateAircraftResponseDto(aircraft.getCode(), aircraft.getType());
    }

    @ApiOperation(
            value = "Delete an aircraft",
            notes = "Provide a code to delete a specific aircraft from the database."
    )
    @DeleteMapping("/{code}")
    public void delete(@Validated @PathVariable String code) {
        this.aircraftService.delete(code);
    }

    @ApiOperation(
            value = "Delete an aircraft type",
            notes = "Provide a model name of the type. This will also delete all aircraft of this type."
    )
    @DeleteMapping("/type/{model}")
    public void deleteType(@Validated @PathVariable String model) {
        this.aircraftService.deleteByType(model);
        this.typeService.delete(model);
    }

    @ApiOperation(value = "Get an overview of all aircraft")
    @GetMapping
    public AircraftOverviewResponseDto findAll() {
        return new AircraftOverviewResponseDto(this.aircraftService.findAll());
    }

    @ApiOperation(
            value = "Get aircraft information",
            notes = "Provide a model name of the aircraft to look up a specific aircraft in the database."
    )
    @GetMapping("/{modelName}")
    public AircraftOverviewByTypeResponseDto findAllByModel(@PathVariable String modelName) {
        return new AircraftOverviewByTypeResponseDto(modelName, this.aircraftService.findAllByType(modelName));
    }

    @ApiOperation(
            value = "Create an aircraft",
            notes = "Provide the details of an aircraft to create an aircraft of a type. " +
                    "Note that the type must exist before you can make an aircraft of it."
    )
    @PostMapping
    public CreateAircraftResponseDto create(@Validated @RequestBody CreateAircraftDto dto) {
        var aircraft = this.aircraftService.create(dto.code, dto.modelName);
        return new CreateAircraftResponseDto(aircraft.getCode(), aircraft.getType());
    }

    @ApiOperation(
            value = "Create an aircraft type",
            notes = "Provide the details of a type to create one."
    )
    @PostMapping("/type")
    public CreateTypeResponseDto create(@Validated @RequestBody CreateTypeDto dto) {
        var type = this.typeService.create(
                dto.modelName,
                dto.manufacturer,
                dto.fuelCapacity,
                dto.fuelConsumption,
                dto.numSeatsFirst,
                dto.numSeatsBusiness,
                dto.numSeatsEconomy
        );
        return new CreateTypeResponseDto(type);
    }
}
