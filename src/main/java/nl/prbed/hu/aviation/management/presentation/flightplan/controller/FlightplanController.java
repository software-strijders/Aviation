package nl.prbed.hu.aviation.management.presentation.flightplan.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.management.application.FlightplanService;
import nl.prbed.hu.aviation.management.domain.Flightplan;
import nl.prbed.hu.aviation.management.presentation.flightplan.dto.FlightplanDto;
import nl.prbed.hu.aviation.management.presentation.flightplan.dto.FlightplanResponseDto;
import nl.prbed.hu.aviation.management.presentation.flightplan.dto.FlightplansResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightplan")
@AllArgsConstructor
public class FlightplanController {
    private final FlightplanService flightplanService;

    @ApiOperation(
            value = "Delete a flightplan",
            notes = "Provide the code of the flightplan to delete it."
    )
    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code) {
        this.flightplanService.deleteByCode(code);
    }

    @ApiOperation(value = "Find all flightplans")
    @GetMapping
    public FlightplansResponseDto findAll() {
        var flightplans = this.flightplanService.findAll();
        return new FlightplansResponseDto(flightplans);
    }

    @ApiOperation(
            value = "Find a flightplan",
            notes = "Provide the code to find a specific flightplan."
    )
    @GetMapping("/{code}")
    public FlightplanResponseDto findByCode(@PathVariable String code) {
        return this.createFlightplanResponseDto(this.flightplanService.findFlightplanByCode(code));
    }

    @ApiOperation(
            value = "Create a flightplan",
            notes = "Note that the cities provided must exist before the flightplan can be created."
    )
    @PostMapping
    public FlightplanResponseDto create(@RequestBody FlightplanDto dto) {
        return createFlightplanResponseDto(this.flightplanService.create(
                dto.code,
                dto.duration,
                dto.arrival,
                dto.destination
        ));
    }

    @ApiOperation(value = "Update a flightplan")
    @PutMapping("/{code}")
    public FlightplanResponseDto update(@PathVariable String code, @RequestBody FlightplanDto dto) {
        return this.createFlightplanResponseDto(this.flightplanService.update(
                code,
                dto.code,
                dto.duration,
                dto.arrival,
                dto.destination
        ));
    }

    private FlightplanResponseDto createFlightplanResponseDto(Flightplan flightplan) {
        return new FlightplanResponseDto(
                flightplan.getCode(),
                flightplan.getDuration(),
                flightplan.getArrival(),
                flightplan.getDestination()
        );
    }
}
