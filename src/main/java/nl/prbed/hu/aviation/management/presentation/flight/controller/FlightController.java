package nl.prbed.hu.aviation.management.presentation.flight.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.FlightService;
import nl.prbed.hu.aviation.management.domain.flight.Flight;
import nl.prbed.hu.aviation.management.presentation.flight.dto.FlightDto;
import nl.prbed.hu.aviation.management.presentation.flight.dto.FlightResponseDto;
import nl.prbed.hu.aviation.management.presentation.flight.mapper.CreateFlightDtoMapper;
import nl.prbed.hu.aviation.management.presentation.flight.dto.FlightsResponseDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {
    private final CreateFlightDtoMapper mapper = CreateFlightDtoMapper.instance;
    private final FlightService flightService;

    @ApiOperation(
            value = "Create a flight",
            notes = "Provide flight information to create a new flight."
    )
    @PostMapping
    public FlightResponseDto create(@Validated @RequestBody FlightDto dto) {
        var flight = this.flightService.create(this.mapper.toFlightStruct(dto));
        return createFlightResponseDto(flight);
    }

    @ApiOperation(
            value = "Update a flight",
            notes = "Provide flight information to update the flight"
    )
    @PatchMapping("/{code}")
    public FlightResponseDto update(@Validated @RequestBody FlightDto dto, @Validated @PathVariable String code) {
        var flight = this.flightService.update(code, this.mapper.toFlightStruct(dto));
        return createFlightResponseDto(flight);
    }

    @ApiOperation(
            value = "Find a flight by code",
            notes = "Provide a code to get the flight."
    )
    @GetMapping("/{code}")
    public FlightResponseDto getFlightByCode(@PathVariable String code) {
        var flight = this.flightService.getFlightByCode(code);
        return new FlightResponseDto(
                flight.getCode(),
                flight.getPriceEconomy(),
                flight.getPriceBusiness(),
                flight.getPriceFirst(),
                flight.getAircraft().getCode(),
                flight.getFlightplan().getCode()
        );
    }

    @ApiOperation(
            value = "Find flights with a certain departure airport",
            notes = "Provide a airport code to find all flights with that airport as it's departure."
    )
    @GetMapping("/departure/{code}")
    public FlightsResponseDto findByDeparture(@PathVariable String code) {
        var flights = this.flightService.findFlightsByDeparture(code);
        return new FlightsResponseDto(flights);
    }

    @ApiOperation(
            value = "Find flights with a certain destination airport",
            notes = "Provide a airport code to find all flights with that airport as it's destination."
    )
    @GetMapping("/destination/{code}")
    public FlightsResponseDto findByDestination(@PathVariable String code) {
        var flights = this.flightService.findFlightsByDestination(code);
        return new FlightsResponseDto(flights);
    }

    @ApiOperation(value = "Find all flights")
    @GetMapping
    public FlightsResponseDto findAll() {
        var flights = this.flightService.findAllFlights();
        return new FlightsResponseDto(flights);
    }

    @ApiOperation(
            value = "Delete a flight",
            notes = "Provide the code of the flight"
    )
    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code) {
        this.flightService.deleteByCode(code);
    }

    private FlightResponseDto createFlightResponseDto (Flight flight) {
        return new FlightResponseDto(
                flight.getCode(),
                flight.getPriceFirst(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getAircraft().getCode(),
                flight.getFlightplan().getCode()
        );
    }
}
