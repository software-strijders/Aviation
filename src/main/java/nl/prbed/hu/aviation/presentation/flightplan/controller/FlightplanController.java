package nl.prbed.hu.aviation.presentation.flightplan.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.FlightplanService;
import nl.prbed.hu.aviation.domain.Flightplan;
import nl.prbed.hu.aviation.presentation.flightplan.dto.FlightplanDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.FindAllFlightplanResponseDto;
import nl.prbed.hu.aviation.presentation.flightplan.dto.FlightplanFindByCodeResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightplan")
@AllArgsConstructor
public class FlightplanController {
    private final FlightplanService flightplanService;

    //TODO: fix destination and arrival
    @ApiOperation(
            value = "Create a flightplan",
            notes = "Note that the cities provided must exist before the flightplan can be created."
    )
    @PostMapping
    public FlightplanFindByCodeResponseDto create(@RequestBody FlightplanDto dto) {
        return createFlightplanResponseDto(this.flightplanService.create(
                dto.code,
                dto.duration,
                dto.arrival,
                dto.destination
        ));
    }

    @ApiOperation(value = "Get all flightplans")
    @GetMapping
    public FindAllFlightplanResponseDto findAll() {
        var flightplans = this.flightplanService.findAll();
        return new FindAllFlightplanResponseDto(flightplans);
    }

    //TODO: fix destination and arrival
    @ApiOperation(
            value = "Get a flightplan",
            notes = "Provide a code to find a specific flightplan."
    )
    @GetMapping("/{code}")
    public FlightplanFindByCodeResponseDto findByCode(@PathVariable String code) {
        return createFlightplanResponseDto(this.flightplanService.findByCode(code));
    }

    @ApiOperation(value = "Update a flightplan")
    //TODO: fix destination and arrival
    @PutMapping
    public FlightplanFindByCodeResponseDto update(@RequestBody FlightplanDto dto) {
        return createFlightplanResponseDto(this.flightplanService.update(
                dto.code,
                dto.duration,
                dto.arrival,
                dto.destination
        ));
    }

    @ApiOperation(
            value = "Delete a flight plan",
            notes = "Provide the code of the flightplan to delete it."
    )
    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code) {
        this.flightplanService.deleteByCode(code);
    }

    private FlightplanFindByCodeResponseDto createFlightplanResponseDto(Flightplan flightplan) {
        return new FlightplanFindByCodeResponseDto(
                flightplan.getCode(),
                flightplan.getDuration(),
                flightplan.getArrival(),
                flightplan.getDestination()
        );
    }
}
