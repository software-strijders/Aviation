package nl.prbed.hu.aviation.presentation.aircraft.controller;

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

    @PutMapping({"/{code}"})
    public UpdateAircraftResponseDto update(@Validated @PathVariable @NonNull String code, @Validated @RequestBody UpdateAircraftDto dto) {
        var aircraft = this.aircraftService.update(code, dto.code, dto.modelName);
        return new UpdateAircraftResponseDto(aircraft.getCode(), aircraft.getType());
    }

    @DeleteMapping("/{code}")
    public void delete(@Validated @PathVariable String code) {
        this.aircraftService.delete(code);
    }

    @DeleteMapping("/type/{model}")
    public void deleteType(@Validated @PathVariable String model) {
        this.aircraftService.deleteByType(model);
        this.typeService.delete(model);
    }

    @GetMapping
    public AircraftOverviewResponseDto findAll() {
        return new AircraftOverviewResponseDto(this.aircraftService.findAll());
    }

    @GetMapping("/{modelName}")
    public AircraftOverviewByTypeResponseDto findAllByModel(@PathVariable String modelName) {
        return new AircraftOverviewByTypeResponseDto(modelName, this.aircraftService.findAllByType(modelName));
    }

    @PostMapping
    public CreateAircraftResponseDto create(@Validated @RequestBody CreateAircraftDto dto) {
        var aircraft = this.aircraftService.create(dto.code, dto.modelName);
        return new CreateAircraftResponseDto(aircraft.getCode(), aircraft.getType());
    }

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
