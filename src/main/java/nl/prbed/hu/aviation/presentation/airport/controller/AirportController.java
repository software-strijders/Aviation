package nl.prbed.hu.aviation.presentation.airport.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.AirportService;
import nl.prbed.hu.aviation.application.CityService;
import nl.prbed.hu.aviation.presentation.dto.CreateAirportDto;
import nl.prbed.hu.aviation.presentation.dto.CreateCityDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airport")
@AllArgsConstructor
public class AirportController {
    private final AirportService airportService;
    private final CityService cityService;

    @PostMapping
    public void create(@Validated @RequestBody CreateAirportDto dto) {
        airportService.create(
                dto.code,
                dto.latitude,
                dto.longitude,
                dto.cityName
                );
    }

    @PostMapping("/city")
    public void create(@Validated @RequestBody CreateCityDto dto) {
        cityService.create(
                dto.name,
                dto.country
        );
    }
}
