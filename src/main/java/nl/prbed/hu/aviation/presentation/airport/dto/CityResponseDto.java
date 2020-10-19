package nl.prbed.hu.aviation.presentation.airport.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Airport;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CityResponseDto {
    private final String name;
    private final String country;
    private final List<Airport> airports;
}