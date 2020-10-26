package nl.prbed.hu.aviation.management.presentation.flight.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.FlightService;
import nl.prbed.hu.aviation.management.presentation.flight.dto.FlightDto;
import nl.prbed.hu.aviation.management.presentation.flight.dto.FlightResponseDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    @ApiOperation(
            value = "Create a flight",
            notes = "A flight information to create a new flight."
    )
    @PostMapping
    public FlightResponseDto create(@Validated @RequestBody FlightDto dto) {
        var flight = this.flightService.create(
                dto.code,
                dto.priceFirst,
                dto.priceBusiness,
                dto.priceEconomy,
                dto.aircraftCode,
                dto.flightPlanCode
        );
        return new FlightResponseDto(
                flight.getId(),
                flight.getCode(),
                flight.getPriceFirst(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getAircraft().getCode(),
                flight.getFlightplan().getCode()
        );
    }

    @ApiOperation(
            value = "Delete a flight",
            notes = "Provide the code of the flight"
    )
    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code) {
        this.flightService.deleteByCode(code);
    }
}
