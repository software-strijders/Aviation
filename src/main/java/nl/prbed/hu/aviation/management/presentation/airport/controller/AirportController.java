package nl.prbed.hu.aviation.management.presentation.airport.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.AirportService;
import nl.prbed.hu.aviation.management.application.CityService;
import nl.prbed.hu.aviation.management.domain.Airport;
import nl.prbed.hu.aviation.management.presentation.airport.dto.*;
import nl.prbed.hu.aviation.management.presentation.airport.mapper.CreateAirportDtoMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airport")
@RequiredArgsConstructor
public class AirportController {
    private final CreateAirportDtoMapper mapper = CreateAirportDtoMapper.instance;
    private final AirportService airportService;
    // TODO: This should probably go into its own controller:
    private final CityService cityService;

    @ApiOperation(
            value = "Delete an airport",
            notes = "Provide a code to delete the desired airport."
    )
    @DeleteMapping("/{airportCode}")
    public void deleteAirportByCode(@PathVariable String airportCode) {
        this.airportService.deleteByCode(airportCode);
    }

    @ApiOperation(
            value = "Delete a city",
            notes = "Provide the name of the city. " +
                    "Note that all airports within that city will also be deleted."
    )
    @DeleteMapping("/city/{cityName}")
    public void deleteAirportsByCity(@PathVariable String cityName) {
        this.cityService.deleteByName(cityName);
    }

    @ApiOperation(
            value = "Delete aircraft from airport",
            notes = "Provide the code of the airport and the code of the aircraft."
    )
    @DeleteMapping("/{code}/aircraft")
    public AirportResponseDto removeAircraftFromAirport(@PathVariable String code, @RequestBody AircraftDto dto) {
        var airport = this.airportService.removeAircraftFromAirport(code, dto.code);
        return this.createAirportResponseDto(airport);
    }

    @ApiOperation(value = "Find all airports")
    @GetMapping
    public AirportsResponseDto findAll() {
        return new AirportsResponseDto(this.airportService.findAll());
    }

    @ApiOperation(
            value = "Find an airport",
            notes = "Provide the code of a specific airport."
    )
    @GetMapping("/{code}")
    public AirportResponseDto findByCode(@PathVariable String code) {
        var airport = this.airportService.findByCode(code);
        return this.createAirportResponseDto(airport);
    }

    @ApiOperation(
            value = "Find all airports by city",
            notes = "Provide a city name."
    )
    @GetMapping("/city/{cityName}")
    public AirportsResponseDto findByCity(@PathVariable String cityName) {
        return new AirportsResponseDto(this.airportService.findByCity(cityName));
    }

    @ApiOperation(
            value = "Update a city",
            notes = "Provide the code of the city that needs to be updated."
    )
    @PatchMapping("/{code}")
    public AirportResponseDto update(@Validated @PathVariable String code, @Validated @RequestBody AirportDto dto) {
        var airport = this.airportService.update(code, this.mapper.toAirportStruct(dto));
        return this.createAirportResponseDto(airport);
    }

    @ApiOperation(
            value = "Add aircraft to airport",
            notes = "Provide codes of all aircraft and a code of the airport."
    )
    @PatchMapping("/{code}/aircraft")
    public AirportResponseDto addAircraftToAirport(@PathVariable String code, @RequestBody AircraftListDto dto) {
        var airport = this.airportService.addAircraftToAirport(code, dto.codes);
        return this.createAirportResponseDto(airport);
    }

    @ApiOperation(
            value = "Create an airport",
            notes = "Provide the details of the booking. " +
                    "Note that the city provided must exist before the airport can be created."
    )
    @PostMapping
    public AirportResponseDto create(@Validated @RequestBody AirportDto dto) {
        var airport = this.airportService.create(this.mapper.toAirportStruct(dto));
        return this.createAirportResponseDto(airport);
    }

    @ApiOperation(
            value = "Create a city",
            notes = "Provide the details of the city."
    )
    @PostMapping("/city")
    public CityResponseDto create(@Validated @RequestBody CreateCityDto dto) {
        var city = this.cityService.create(dto.name, dto.country);
        return new CityResponseDto(city.getName(), city.getCountry(), city.getAirports());
    }

    private AirportResponseDto createAirportResponseDto(Airport airport) {
        return new AirportResponseDto(
                airport.getCode(),
                airport.getLatitude(),
                airport.getLongitude(),
                airport.getCity(),
                airport.getAircraft()
        );
    }
}
