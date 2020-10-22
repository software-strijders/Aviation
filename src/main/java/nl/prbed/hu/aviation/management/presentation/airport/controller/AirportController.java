package nl.prbed.hu.aviation.management.presentation.airport.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.AirportService;
import nl.prbed.hu.aviation.management.application.CityService;
import nl.prbed.hu.aviation.management.presentation.airport.dto.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airport")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;
    // TODO: This should probably go into its own controller:
    private final CityService cityService;

    @ApiOperation(
            value = "Delete an airport",
            notes = "Provide a code to delete the desired airport"
    )
    @DeleteMapping("/{airportCode}")
    public void deleteAirport(@PathVariable String airportCode) {
        this.airportService.delete(airportCode);
    }

    @ApiOperation(
            value = "Delete a city",
            notes = "Provide a name to delete the desired city. " +
                    "Note that all airports within that city will also be deleted."
    )
    @DeleteMapping("/city/{cityName}")
    public void deleteAirportsByCity(@PathVariable String cityName) {
        this.cityService.delete(cityName);
    }

    @ApiOperation(value = "Find all airports")
    @GetMapping
    public AirportsResponseDto findAll() {
        return new AirportsResponseDto(this.airportService.findAll());
    }

    @ApiOperation(
            value = "Find an airport",
            notes = "Provide a code of a specific airport"
    )
    @GetMapping("/{code}")
    public AirportResponseDto findByCode(@PathVariable String code) {
        var airport = this.airportService.findByCode(code);
        return new AirportResponseDto(airport.getCode(), airport.getLatitude(), airport.getLongitude(), airport.getCity(), null);
    }

    @ApiOperation(
            value = "Find all airports by city",
            notes = "Provide a city name"
    )
    @GetMapping("/city/{cityName}")
    public AirportsResponseDto findByCity(@PathVariable String cityName) {
        return new AirportsResponseDto(this.airportService.findByCity(cityName));
    }

    @ApiOperation(
            value = "Create an airport",
            notes = "Note that the city provided must exist before the airport can be created."
    )
    @PostMapping
    public AirportResponseDto create(@Validated @RequestBody CreateAirportDto dto) {
        var airport = this.airportService.create(dto.code, dto.latitude, dto.longitude, dto.cityName);
        return new AirportResponseDto(airport.getCode(), airport.getLatitude(), airport.getLongitude(), airport.getCity(), null);
    }

    @ApiOperation(value = "Create a city")
    @PostMapping("/city")
    public CityResponseDto create(@Validated @RequestBody CreateCityDto dto) {
        var city = this.cityService.create(dto.name, dto.country);
        return new CityResponseDto(city.getName(), city.getCountry(), city.getAirports());
    }

    @ApiOperation(
            value = "Update a city",
            notes = "Provide a code of the city that needs to be updated."
    )
    @PutMapping("/{code}")
    public AirportResponseDto update(@Validated @PathVariable String code, @Validated @RequestBody CreateAirportDto dto) {
        var airport = this.airportService.update(code, dto.code, dto.latitude, dto.longitude, dto.cityName, dto.aircraftCodes);
        return new AirportResponseDto(airport.getCode(), airport.getLatitude(), airport.getLongitude(), airport.getCity(), airport.getAircraft());
    }
}
