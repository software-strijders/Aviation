package nl.prbed.hu.aviation.management.presentation.airport.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.domain.City;

@Getter
@RequiredArgsConstructor
public class UpdateAirportResponseDto {
    private final String code;
    private final double latitude;
    private final double longitude;
    private final City city;
}
