package nl.prbed.hu.aviation.presentation.airport.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.application.AirportService;
import nl.prbed.hu.aviation.application.CityService;
import nl.prbed.hu.aviation.presentation.airport.dto.*;
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

    @ApiOperation(
            value = "Create an airport",
            notes = "Note that the city provided must exist before the airport can be created."
    )
    @PostMapping
    public AirportResponseDto create(@Validated @RequestBody CreateAirportDto dto) {
        var airport = this.airportService.create(dto.code, dto.latitude, dto.longitude, dto.cityName);
        return new AirportResponseDto(airport.getCode(), airport.getLatitude(), airport.getLongitude(),
                airport.getCity().getName());
    }

    @ApiOperation(value = "Create a city")
    @PostMapping("/city")
    public CityResponseDto create(@Validated @RequestBody CreateCityDto dto) {
        var city = this.cityService.create(dto.name, dto.country);
        return new CityResponseDto(city.getName(), city.getCountry(), city.getAirports());
    }

    @GetMapping
    public AirportsResponseDto findAll() {
        return new AirportsResponseDto(this.airportService.findAll());
    }

    @GetMapping("/{code}")
    public AirportResponseDto findByCode(@PathVariable String code) {
        var airport = this.airportService.findByCode(code);
        return new AirportResponseDto(airport.getCode(), airport.getLatitude(), airport.getLongitude(),
                airport.getCity().getName());
    }

    @GetMapping("/city/{cityName}")
    public AirportsResponseDto findByCity(@PathVariable String cityName) {
        return new AirportsResponseDto(this.airportService.findByCity(cityName));
    }
}
