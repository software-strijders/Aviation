package nl.prbed.hu.aviation.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseAirportDto {
    private final String code;
    private final float latitude;
    private final float longitude;
    private final String cityName;
}
