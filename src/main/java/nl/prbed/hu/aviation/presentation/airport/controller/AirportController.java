package nl.prbed.hu.aviation.presentation.airport.controller;

import lombok.AllArgsConstructor;
import nl.prbed.hu.aviation.application.AirportService;
import nl.prbed.hu.aviation.application.CityService;
import nl.prbed.hu.aviation.domain.Airport;
import nl.prbed.hu.aviation.presentation.dto.CreateAirportDto;
import nl.prbed.hu.aviation.presentation.dto.CreateCityDto;
import nl.prbed.hu.aviation.presentation.dto.ResponseAirportDto;
import nl.prbed.hu.aviation.presentation.dto.ResponseCityDto;
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
    public ResponseAirportDto create(@Validated @RequestBody CreateAirportDto dto) {
        var airport = airportService.create(dto.code, dto.latitude, dto.longitude, dto.cityName);
        return new ResponseAirportDto(airport.getCode(), airport.getLatitude(), airport.getLongitude(),
                airport.getCity().getName());
    }

    @PostMapping("/city")
    public ResponseCityDto create(@Validated @RequestBody CreateCityDto dto) {
        var city = cityService.create(dto.name, dto.country);
        return new ResponseCityDto(city.getName(), city.getCountry(), city.getAirports());
    }
}
