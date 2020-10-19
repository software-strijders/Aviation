package nl.prbed.hu.aviation.presentation.airport.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.domain.Airport;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AirportsResponseDto {
    private final List<Airport> airports;
}
