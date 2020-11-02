package nl.prbed.hu.aviation.management.presentation.flight.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.FlightService;
import nl.prbed.hu.aviation.management.domain.flight.Flight;
import nl.prbed.hu.aviation.management.presentation.flight.dto.FlightDto;
import nl.prbed.hu.aviation.management.presentation.flight.dto.FlightResponseDto;
import nl.prbed.hu.aviation.management.presentation.flight.mapper.FlightDtoMapper;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasBuilder;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasDirector;
import nl.prbed.hu.aviation.management.presentation.hateoas.HateoasType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@Secured("ROLE_EMPLOYEE")
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {
    private final FlightDtoMapper mapper = FlightDtoMapper.instance;
    private final FlightService flightService;
    private final HateoasDirector hateoasDirector = new HateoasDirector(new HateoasBuilder(), this.getClass());

    @ApiOperation(
            value = "Delete a flight",
            notes = "Provide the code of the flight."
    )
    @DeleteMapping("/{code}")
    public void deleteByCode(@PathVariable String code) {
        this.flightService.deleteByCode(code);
    }

    @ApiOperation(
            value = "Find a flight by code",
            notes = "Provide the code of the flight."
    )
    @GetMapping("/{code}")
    public EntityModel<FlightResponseDto> findFlightByCode(@PathVariable String code) {
        var flight = this.flightService.findFlightByCode(code);
        var response = this.createFlightResponseDto(flight);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ONE, flight.getCode()));
    }

    @ApiOperation(
            value = "Find flights with a specific departure airport",
            notes = "Provide an airport code."
    )
    @GetMapping("/departure/{code}")
    public CollectionModel<EntityModel<FlightResponseDto>> findByDeparture(@PathVariable String code) {
        var flights = this.flightService.findFlightsByDeparture(code);
        var response = flights.stream()
                .map(this::createFlightResponseDto)
                .map(dto -> EntityModel.of(dto, this.hateoasDirector.make(HateoasType.FIND_ONE, dto.getCode())))
                .collect(Collectors.toList());
        return CollectionModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ALL, "departure"));
    }

    @ApiOperation(
            value = "Find flights with a specific destination airport",
            notes = "Provide an airport code."
    )
    @GetMapping("/destination/{code}")
    public CollectionModel<EntityModel<FlightResponseDto>> findByDestination(@PathVariable String code) {
        var flights = this.flightService.findFlightsByDestination(code);
        var response = flights.stream()
                .map(this::createFlightResponseDto)
                .map(dto -> EntityModel.of(dto, this.hateoasDirector.make(HateoasType.FIND_ONE, dto.getCode())))
                .collect(Collectors.toList());
        return CollectionModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ALL, "destination"));
    }

    @ApiOperation(value = "Find all flights")
    @GetMapping
    public CollectionModel<EntityModel<FlightResponseDto>> findAll() {
        var flights = this.flightService.findAllFlights();
        var response = flights.stream()
                .map(this::createFlightResponseDto)
                .map(dto -> EntityModel.of(dto, this.hateoasDirector.make(HateoasType.FIND_ONE, dto.getCode())))
                .collect(Collectors.toList());
        return CollectionModel.of(response, this.hateoasDirector.make(HateoasType.FIND_ALL));
    }

    @ApiOperation(
            value = "Update a flight",
            notes = "Provide flight information to update the flight."
    )
    @PatchMapping("/{code}")
    public EntityModel<FlightResponseDto> update(@Validated @RequestBody FlightDto dto, @Validated @PathVariable String code) {
        var flight = this.flightService.update(code, this.mapper.toFlightStruct(dto));
        var response = this.createFlightResponseDto(flight);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.UPDATE, response.getCode()));
    }

    @ApiOperation(
            value = "Create a flight",
            notes = "Provide flight information to create a new flight."
    )
    @PostMapping
    public EntityModel<FlightResponseDto> create(@Validated @RequestBody FlightDto dto) {
        var flight = this.flightService.create(this.mapper.toFlightStruct(dto));
        var response = this.createFlightResponseDto(flight);
        return EntityModel.of(response, this.hateoasDirector.make(HateoasType.CREATE, response.getCode()));
    }

    private FlightResponseDto createFlightResponseDto(Flight flight) {
        return new FlightResponseDto(
                flight.getId(),
                flight.getCode(),
                flight.getPriceFirst(),
                flight.getPriceBusiness(),
                flight.getPriceEconomy(),
                flight.getDepartureDateTime(),
                flight.getAircraft().getCode(),
                flight.getFlightplan().getCode()
        );
    }
}
