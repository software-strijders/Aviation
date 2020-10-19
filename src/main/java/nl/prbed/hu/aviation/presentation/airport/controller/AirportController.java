package nl.prbed.hu.aviation.presentation.airport.controller;

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

    @DeleteMapping("/{airportCode}")
    public void deleteAirport(@PathVariable String airportCode) {
        this.airportService.delete(airportCode);
    }

    @DeleteMapping("/city/{cityName}")
    public void deleteAirportsByCity(@PathVariable String cityName) {
        this.cityService.delete(cityName);
    }

    @PostMapping
    public AirportResponseDto create(@Validated @RequestBody CreateAirportDto dto) {
        var airport = this.airportService.create(dto.code, dto.latitude, dto.longitude, dto.cityName);
        return new AirportResponseDto(airport.getCode(), airport.getLatitude(), airport.getLongitude(),
                airport.getCity().getName());
    }

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
