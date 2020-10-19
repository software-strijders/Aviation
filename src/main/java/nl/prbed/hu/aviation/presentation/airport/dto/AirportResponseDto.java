package nl.prbed.hu.aviation.presentation.airport.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AirportResponseDto {
    private final String code;
    private final float latitude;
    private final float longitude;
    private final String cityName;
}
